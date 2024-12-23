package com.apricot.store.Controller;

import com.apricot.store.Controller.ex.FileEmptyException;
import com.apricot.store.Controller.ex.FileSizeException;
import com.apricot.store.Controller.ex.FileTypeException;
import com.apricot.store.Controller.ex.FileUploadIOException;
import com.apricot.store.Entity.User;
import com.apricot.store.Entity.dto.UserChangeInfoDto;
import com.apricot.store.Entity.dto.UserChangePwdDto;
import com.apricot.store.Entity.dto.UserLoginDto;
import com.apricot.store.Entity.dto.UserRegisterDto;
import com.apricot.store.Service.IUserService;
import com.apricot.store.Utils.JsonResult;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;

    /*@RequestMapping("/register")
    public JsonResult<Void> register(User user) {
        JsonResult<Void> result = new JsonResult<Void>();
        try {
            userService.register(user);
            result.setStatus(200);
            result.setMessage("注册成功");
        } catch (UsernameDuplicatedException e) {
            result.setStatus(401);
            result.setMessage(e.getMessage());
        } catch (InsertException e) {
            result.setStatus(500);
            result.setMessage(e.getMessage());
        }
        return result;
    }*/
    @PostMapping("/register")
    //@ResponseBody
    public JsonResult register(@RequestBody UserRegisterDto dto) {
        //System.out.println("Now regging " + dto.getUsername());
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        userService.register(user);
        JsonResult result = new JsonResult(SUCCESS_CODE, "注册成功", null);
        //System.out.println(result);
        return result;
    }

    @PostMapping("/login")
    public JsonResult login(@RequestBody UserLoginDto dto, HttpSession session) {
        //System.out.println("Now logging in " + dto.getUsername());
        User user = userService.login(dto.getUsername(), dto.getPassword());
        session.setAttribute("uid", user.getUid());
        session.setAttribute("username", user.getUsername());
        session.setAttribute("avatar", user.getAvatar());
        JsonResult result = new JsonResult(SUCCESS_CODE, "登录成功", dto);
        //System.out.println(result);
        return result;
    }

    @PutMapping("/updatePassword")
    public JsonResult changePassword(@RequestBody UserChangePwdDto dto, HttpSession session) {
        userService.updatePassword(getUserIdFromSession(session),
                getUsernameFromSession(session),
                dto.getOldPassword(),
                dto.getNewPassword());
        return new JsonResult(SUCCESS_CODE, "密码修改成功", null);
    }

    @PutMapping("/updateProfile")
    public JsonResult updateInfo(@RequestBody UserChangeInfoDto dto, HttpSession session) {
        User user = new User();
        user.setGender(dto.getGender());
        user.setPhone(dto.getPhone());
        user.setEmail(dto.getEmail());

        userService.updateProfile(getUserIdFromSession(session), user);
        return new JsonResult(SUCCESS_CODE, "信息修改成功", null);
    }

    @GetMapping("/getProfile")
    public JsonResult getProfile(HttpSession session) {
        //System.out.println("uid = " + getUserIdFromSession(session));
        UserChangeInfoDto user = userService.getInfoByUid(getUserIdFromSession(session));
        return new JsonResult(SUCCESS_CODE, "获取个人信息成功", user);
    }


    // 规定头像上传大小为10M
    public static final int AVATAR_MAX_SIZE = 1024 * 1024 * 10; // 10M
    // 规定头像上传格式为jpg、jpeg、png
    public static final List<String> AVATAR_ALLOWED_TYPES = List.of("image/jpeg",
                                                                    "image/png",
                                                                    "image/bmp",
                                                                    "image/gif");


    @Value(value = "${avatar.upload.path}")
    private String path;

    /**
     * MultipartFile 是 Spring MVC 提供的上传文件对象，
     * 可以直接通过MultipartFile file 接收文件。
     * 这里的 @RequestParam("avatar") 注解的括号中可以指定上传文件的名称，
     * 该名称必须和前端的input标签的name属性值一致,从而后端可以取别名。
     * @param session
     * @param file
     * @return
     */
    @PostMapping("/updateAvatar")
    public JsonResult updateAvatar(HttpSession session,
                                   /*@RequestParam("avatar")*/ MultipartFile file) {
        // 校验文件是否为空
        if (file.isEmpty()) {
            throw new FileEmptyException("上传文件为空");
        }
        // 校验文件大小
        if (file.getSize() > AVATAR_MAX_SIZE) {
            throw new FileSizeException("上传文件大小超出限制");
        }
        // 校验文件格式
        String contentType = file.getContentType();
        if (!AVATAR_ALLOWED_TYPES.contains(contentType)) {
            throw new FileTypeException("上传文件格式不支持");
        }

        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        String uuid = java.util.UUID.randomUUID()
                    .toString()
                    .toUpperCase()
                    .replaceAll("-", "")
                    + suffix;

        // 使用transferTo方法保存文件到指定目录
        try {
            file.transferTo(new File(dir, uuid));
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileUploadIOException("上传文件失败");
        }
        // 更新用户头像
        User user = new User();
        user.setAvatar(uuid);
        userService.updateAvatar(getUserIdFromSession(session), uuid, getUsernameFromSession(session));
        return new JsonResult(SUCCESS_CODE, "头像修改成功", uuid);
    }

}