package com.swedist.computer_lab_gradle.service;

import com.swedist.computer_lab_gradle.dto.request.PasswordUpdateRequest;
import com.swedist.computer_lab_gradle.dto.UserDTO;
import com.swedist.computer_lab_gradle.dto.request.UserUpdateRequest;
import com.swedist.computer_lab_gradle.entity.AppRole;
import com.swedist.computer_lab_gradle.entity.AppUser;
import com.swedist.computer_lab_gradle.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;

import static org.apache.commons.io.FilenameUtils.getExtension;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(UserDTO::new).toList();
    }

    public UserDTO updateUser(UserUpdateRequest userUpdateRequest, Long userId) {
        AppUser user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found")); //todo handle exception
        if (userUpdateRequest.getFullName() != null) {
            user.setFullName(userUpdateRequest.getFullName());
        }
        if (userUpdateRequest.getDepartment() != null) {
            user.setDepartment(userUpdateRequest.getDepartment());
        }
        return new UserDTO(userRepository.save(user));
    }

    public void updatePassword(PasswordUpdateRequest passwordUpdateRequest, Long userId) {
        AppUser user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found")); //todo handle exception
        if (!passwordEncoder.matches(passwordUpdateRequest.getCurrentPassword(), user.getPassword())) {
            throw new RuntimeException("Password is incorrect"); //todo handle exception
        }
        user.setPassword(passwordEncoder.encode(passwordUpdateRequest.getNewPassword()));
        userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public UserDTO setUserImage(MultipartFile picture, String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Authorization header is missing"); //todo handle exception
        }
        String token = authHeader.substring(7);
        String username = jwtService.extractUsername(token);
        AppUser user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found")); //todo handle exception

        String extension = getExtension(picture.getOriginalFilename()); // todo check if extension is valid
        String filename = UUID.randomUUID() + "." + extension;

        try {
            if (user.getImageName() != null) {
                Files.delete(Path.of("src/main/resources/profilePicture/" + user.getImageName()));
            }
        } catch (IOException ignored) {
        }

        try {
            Path path = Path.of("src/main/resources/profilePicture");
            if (!Files.exists(path)) Files.createDirectory(path);
            Files.write(Path.of("src/main/resources/profilePicture/" + filename),
                    picture.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        user.setImageName(filename);
        return new UserDTO(userRepository.save(user));
    }

    public byte[] getPicture(String pictureName) {
        try {
            Path defaultImagePath = Path.of("src/main/resources/static/images/defaultProfilePicture.jpeg");
            if (pictureName == null || pictureName.isBlank()) {
                return Files.readAllBytes(defaultImagePath);
            }

            Path imagePath = Path.of("src/main/resources/profilePicture/" + pictureName);
            if (!Files.exists(imagePath)) {
                return Files.readAllBytes(defaultImagePath);
            }

            return Files.readAllBytes(imagePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public UserDTO setAdminTrue(Long userId) {
        AppUser user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found")); //todo handle exception
        user.add(AppRole.ADMIN);
        return new UserDTO(userRepository.save(user));
    }
}
