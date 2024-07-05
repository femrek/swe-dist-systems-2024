package com.swedist.computer_lab_gradle.dto.request;


import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PasswordUpdateRequest {
    private String currentPassword;
    private String newPassword;
}
