package com.apricot.store.Entity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class UserChangePwdDto {
    private String oldPassword;
    private String newPassword;
}
