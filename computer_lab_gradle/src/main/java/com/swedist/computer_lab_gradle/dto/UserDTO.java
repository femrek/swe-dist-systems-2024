package com.swedist.computer_lab_gradle.dto;

import com.swedist.computer_lab_gradle.entity.AppUser;
import com.swedist.computer_lab_gradle.util.AppUtil;
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
    private String department;
    private String picturePath;

    public UserDTO(AppUser appUser) {
        this.id = appUser.getId();
        this.username = appUser.getUsername();
        this.fullName = appUser.getFullName();
        this.department = appUser.getDepartment();
        this.roles = appUser.getRoles().stream().map(Enum::name).toList();
        this.picturePath = AppUtil.getUserPicturePath(appUser.getImageName());
    }

    public String toVisualString() {
        return new StringBuilder()
                .append(username)
                .append(", ")
                .append(fullName)
                .append(", ")
                .append(department)
                .toString();
    }
}
