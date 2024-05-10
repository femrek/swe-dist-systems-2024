package com.swedist.computer_lab_gradle.dto;

import com.swedist.computer_lab_gradle.entity.AppUser;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String username;
    private String fullName;
    private List<String> roles;

    public UserDTO(AppUser appUser) {
        this.id = appUser.getId();
        this.username = appUser.getUsername();
        this.fullName = appUser.getFullName();
        this.roles = appUser.getRoles().stream().map(Enum::name).toList();
    }
}
