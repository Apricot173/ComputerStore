package com.apricot.store.Controller;

import com.apricot.store.Controller.ex.*;
import com.apricot.store.Service.ex.*;
import com.apricot.store.Utils.JsonResult;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {
    public static final Integer SUCCESS_CODE = 200;

    @ExceptionHandler({ServiceException.class, FileUploadException.class})
    public JsonResult handleServiceException(Throwable e) {
        JsonResult result = new JsonResult(e);
        int status = 1000; // 默认未知异常状态

        if (e instanceof ServiceException) {
            if (e instanceof UsernameDuplicatedException) {
                status = 403;
            } else if (e instanceof InvalidPasswordException) {
                status = 401;
            } else if (e instanceof UserNotFoundException) {
                status = 404;
            } else if (e instanceof TooManyAddressesException) {
                status = 405;
            } else if (e instanceof ProductNotFoundException){
                status = 406;
            } else if (e instanceof ProductUnavailableException) {
                status = 407;
            }
            else {
                status = 500; // 其他服务异常
            }
            result.setMessage(e.getMessage());
        } else if (e instanceof FileUploadException) {
            if (e instanceof FileEmptyException) {
                status = 601;
            } else if (e instanceof FileSizeException) {
                status = 602;
            } else if (e instanceof FileStateException) {
                status = 603;
            } else if (e instanceof FileTypeException) {
                status = 604;
            } else if (e instanceof FileUploadIOException) {
                status = 605;
            } else {
                status = 600; // 未知文件上传异常
            }
            result.setMessage(e.getMessage());
        }

        result.setStatus(status); // 设置状态码
        return result;
    }


    protected final Integer getUserIdFromSession(HttpSession session) {
        return (Integer)session.getAttribute("uid");
    }

    protected final String getUsernameFromSession(HttpSession session) {
        return (String)session.getAttribute("username");
    }
}
