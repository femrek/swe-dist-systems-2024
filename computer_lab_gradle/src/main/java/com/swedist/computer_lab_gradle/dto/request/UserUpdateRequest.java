package com.swedist.computer_lab_gradle.dto.request;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequest {
    private String fullName;
    private String department;
}
