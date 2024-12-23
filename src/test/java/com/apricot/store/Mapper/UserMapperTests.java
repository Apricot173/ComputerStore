package com.apricot.store.Mapper;

import com.apricot.store.Entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@SpringBootTest // 标注当前类是一个SpringBootTest 不随项目打包发送
@RunWith(SpringRunner.class) // 标注使用SpringRunner作为测试运行器
public class UserMapperTests {

    @Autowired
    private UserMapper userMapper;

    /**
     * 单元测试方法
     * 1. 必须被@Test注解
     * 2. 返回值类型必须是void
     * 3. 方法的参数列表不指定任何类型
     * 4. 方法的访问修饰符必须是public
     */
    @Test
    public void insert() {
        User user = new User();
        user.setUsername("testdel");
        user.setPassword("123456");
        Integer result = userMapper.insertUser(user);
        System.out.println(result);
    }

    @Test
    public void findByUsername() {
        User user = userMapper.getByUsername("testacc");
        System.out.println(user);
    }

    @Test
    public void updatePassword() {
        userMapper.updatePasswordByUid(18, "testpassword",
                                        "ADMIN" , new Date());
    }

    @Test
    public void findByUid() {
        User user = userMapper.findByUid(18);
        System.out.println(user);
    }

    @Test
    public void updateUser() {
        User user = new User();
        user.setUid(18);
        user.setPhone("1789789789");
        user.setEmail("test@test.com");
        user.setGender(1);
        Integer result =userMapper.updateInfoByUid(user);
        System.out.println(result);
    }

    @Test
    public void deleteUser() {
        User user = userMapper.findByUid(21);
        Integer result = userMapper.deleteByUid(21, user.getUsername(), new Date());
        System.out.println(result);
    }

    @Test
    public void updateAvatar() {
        User user = userMapper.findByUid(18);
        String avatar = "testavatar.jpg";
        Integer result = userMapper.updateAvatarByUid(18, avatar, user.getUsername(), new Date());
        System.out.println(result);
    }

}
