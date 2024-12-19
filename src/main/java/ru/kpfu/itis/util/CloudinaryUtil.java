package ru.kpfu.itis.util;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.*;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;


public final class CloudinaryUtil {
    private static Cloudinary cloudinary;

    public static Cloudinary getInstance() {
        if (cloudinary == null) {
            Map<String, String> configMap = new HashMap<>();
            configMap.put("cloud_name", "dhuo3yhvw");
            configMap.put("api_key", "251855165333172");
            configMap.put("api_secret", "cO2J95khbIAM3agdd7xfsiJNbGQ");
            cloudinary = new Cloudinary(configMap);
        }
        return cloudinary;
    }
}
