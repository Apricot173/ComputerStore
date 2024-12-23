package com.apricot.store.Service;

import com.apricot.store.Entity.User;
import com.apricot.store.Service.ex.ServiceException;
import com.apricot.store.Service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTests {

    @Autowired
    private IUserService userService;

    @Test
    public void register() {
        try {
            User user = new User();
            user.setUsername("test_del");
            user.setPassword("test_pass");
            userService.register(user);
            System.out.println("OK");
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName() + ": " + e.getMessage());
        }
    }

    @Test
    public void login() {
        try {
            User user = userService.login("test_acc", "test_pass_new");
            System.out.println("OK");
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName() + ": " + e.getMessage());
        }
    }

    @Test
    public void changePassword() {
        try {
            userService.updatePassword(19, "test_acc",
                    "test_pass", "test_pass_new");
            System.out.println("OK");
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName() + ": " + e.getMessage());
        }
    }

    @Test
    public void updateProfile() {
        try {
            User user = new User();
            user.setUid(19);
            user.setUsername("test_acc");
            user.setEmail("test_email");
            user.setPhone("test_phone");
            userService.updateProfile(19, user);
            System.out.println("OK");
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName() + ": " + e.getMessage());
        }
    }

    @Test
    public void updateAvatar() {
        try {
            userService.updateAvatar(19, "test_avatar.jpg", "test_acc");
            System.out.println("OK");
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName() + ": " + e.getMessage());
        }
    }
}
