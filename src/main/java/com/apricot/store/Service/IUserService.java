package com.apricot.store.Service;

import com.apricot.store.Entity.User;
import com.apricot.store.Entity.dto.UserChangeInfoDto;
import org.springframework.stereotype.Service;

public interface IUserService {
    void register(User user);
    User login(String username, String password);

    void updatePassword(Integer uid, String username, String oldPassword, String newPassword);

    void updateProfile(Integer uid, User user);

    void updateAvatar(Integer uid, String avatarUrl, String modifiedBy);

    UserChangeInfoDto getInfoByUid(Integer userIdFromSession);
}
