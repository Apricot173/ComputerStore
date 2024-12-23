package com.apricot.store.Service.impl;

import com.apricot.store.Entity.User;
import com.apricot.store.Entity.dto.UserChangeInfoDto;
import com.apricot.store.Mapper.UserMapper;
import com.apricot.store.Service.IUserService;
import com.apricot.store.Service.ex.UpdateException;
import com.apricot.store.Service.ex.InvalidPasswordException;
import com.apricot.store.Service.ex.UserNotFoundException;
import com.apricot.store.Service.ex.UsernameDuplicatedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;


@Service
public class UserServiceImpl implements IUserService {

    private UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }


    @Override
    public void register(User user) {
        // 判断是否存在该用户
        User isExist = userMapper.getByUsername(user.getUsername());
        if (isExist != null) {
            throw new UsernameDuplicatedException("用户名已存在");
        }

        // 密码加密 MD5
        String unencryptedPassword = user.getPassword();
        String salt = UUID.randomUUID().toString().toUpperCase();
        user.setSalt(salt);
        String encryptedPassword = getMD5Password(unencryptedPassword, salt);
        user.setPassword(encryptedPassword);


        // 补全默认和必要日志信息
        user.setIsDelete(0);
        user.setCreatedUser(user.getUsername());
        user.setModifiedUser(user.getUsername());
        Date now = new Date();
        user.setCreatedTime(now);
        user.setModifiedTime(now);


        // 注册用户
        Integer rows =userMapper.insertUser(user);
        if (rows!= 1) {
            throw new UpdateException("注册用户错误");
        }
    }

    @Override
    public User login(String username, String password) {
        User user = userMapper.getByUsername(username);
        if (user == null || user.getIsDelete() == 1) {
            throw new UserNotFoundException("用户不存在");
        } else if (!getMD5Password(password, user.getSalt()).equals(user.getPassword())) {
            throw new InvalidPasswordException("用户输入的密码错误");
        }
        User result = new User();
        result.setUid(user.getUid());
        result.setUsername(user.getUsername());
        result.setAvatar(user.getAvatar());
        return result;
    }

    /**
     * 更新用户密码
     */
    @Override
    public void updatePassword(Integer uid, String username, String oldPassword, String newPassword) {
        User user = userMapper.findByUid(uid);
        // 判断是否存在该用户
        if (user == null || user.getIsDelete() == 1) {
            throw new UserNotFoundException("用户不存在");
        }
        // 判断原始密码是否正确
        String oldMD5Password = getMD5Password(oldPassword, user.getSalt());
        if (!oldMD5Password.equals(user.getPassword())) {
            throw new InvalidPasswordException("用户输入的原始密码错误");
        }
        // 新密码加密 MD5
        String newMD5Password = getMD5Password(newPassword, user.getSalt());
        Integer rows = userMapper.updatePasswordByUid(uid, newMD5Password, username, new Date());
        if (rows != 1) {
            throw new UpdateException("更新密码错误");
        }
    }

    /**
     * 更新用户信息
     * @param uid 要更改的用户ID
     * @param user 要更改的用户信息
     */
    @Override
    public void updateProfile(Integer uid, User user) {
        User oldUser = userMapper.findByUid(uid);
        // 判断是否存在该用户
        if (oldUser == null || oldUser.getIsDelete() == 1) {
            throw new UserNotFoundException("用户不存在");
        }
        user.setUid(uid);
        user.setModifiedUser(oldUser.getUsername());
        user.setModifiedTime(new Date());
        Integer rows = userMapper.updateInfoByUid(user);
        if (rows != 1) {
            throw new UpdateException("更新用户信息错误");
        }
    }

    /**
     * 更新用户头像
     * @param uid 用户ID,可以从session中获取
     * @param avatarUrl 头像URL
     * @param modifiedBy, 修改人, 可以从session中获取username
     */
    @Override
    public void updateAvatar(Integer uid, String avatarUrl, String modifiedBy) {
        User user = userMapper.findByUid(uid);
        // 判断是否存在该用户
        if (user == null || user.getIsDelete() == 1) {
            throw new UserNotFoundException("用户不存在");
        }
        Integer rows = userMapper.updateAvatarByUid(uid, avatarUrl, modifiedBy, new Date());
        if (rows != 1) {
            throw new UpdateException("更新头像错误");
        }
    }

    @Override
    public UserChangeInfoDto getInfoByUid(Integer userIdFromSession) {
        User user = userMapper.findByUid(userIdFromSession);
        if (user == null || user.getIsDelete() == 1) {
            throw new UserNotFoundException("用户不存在");
        }
        System.out.println("mapper " + user);
        UserChangeInfoDto result = new UserChangeInfoDto();
        result.setUsername(user.getUsername());
        result.setEmail(user.getEmail());
        result.setPhone(user.getPhone());
        result.setGender(user.getGender());
        result.setAvatar(user.getAvatar());
        System.out.println("dto " + result);
        return result;
    }


    // MD5 加密
    // 忽略原有密码的强度
    private String getMD5Password(String password, String salt) {
        for (int i = 0 ; i < 3 ; i ++) {
            password = DigestUtils.md5DigestAsHex((salt +password + salt).getBytes()).toUpperCase();
        }
        return password;
    }

}
