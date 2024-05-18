package com.swedist.computer_lab_gradle.util;

import java.nio.file.Files;
import java.nio.file.Path;

public abstract class AppUtil {
    private final static String PROFILE_PICTURE_PATH = "src/main/resources/profilePicture/";

    public static String getUserPicturePath(String imageName) {
        if (imageName == null || imageName.isBlank()) {
            return "/images/defaultProfilePicture.jpeg";
        }

        Path imagePath = Path.of(PROFILE_PICTURE_PATH + imageName);
        if (!Files.exists(imagePath)) {
            return "/images/defaultProfilePicture.jpeg";
        }

        return "/picture/" + imageName;
    }
}
