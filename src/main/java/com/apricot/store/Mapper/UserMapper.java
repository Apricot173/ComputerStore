package com.apricot.store.Mapper;

import com.apricot.store.Entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * 不使用@Mapper注解，而是在启动类上使用@MapperScan注解扫描包路径下的所有Mapper接口，
 */
public interface UserMapper {
    /**
     * Insert user into database.
     * @param user User
     * @return Integer 受影响的行数
     */
    Integer insertUser(User user);
    /**
     * Find user by username.
     * @param username String
     * @return User
     */
    User getByUsername(String username);
    User findByUid(Integer uid);
    Integer updatePasswordByUid(Integer uid, String password,
                                String modifiedUser, Date modifiedTime);
    Integer updateInfoByUid(User user);
    Integer deleteByUid(Integer uid, String modifiedUser, Date modifiedTime);

    Integer updateAvatarByUid(@Param("uid") Integer uid,
                              @Param("avatar") String avatar,
                              @Param("modifiedUser") String mUser,
                              @Param("modifiedTime") Date mTime);

}
