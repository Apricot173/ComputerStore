package com.apricot.store.Utils;


import com.apricot.store.Controller.BaseController;
import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class JsonResult implements Serializable {
    private Integer status;
    private String message;
    private Object data;

    public JsonResult(Throwable e) {
        this.message = e.getMessage();
    }
}
