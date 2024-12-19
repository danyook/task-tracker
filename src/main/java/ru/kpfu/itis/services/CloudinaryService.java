package ru.kpfu.itis.services;

import com.cloudinary.Cloudinary;
import ru.kpfu.itis.util.CloudinaryUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class CloudinaryService {
    private static CloudinaryService INSTANCE;
    private static final String FILE_PREFIX = "/tmp";
    private static final int DIRECTORIES_COUNT = 10;
    private final Cloudinary cloudinary;

    private CloudinaryService() {
        cloudinary = CloudinaryUtil.getInstance();
    }

    public static CloudinaryService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CloudinaryService();
        }
        return INSTANCE;
    }

    public String uploadPhoto(HttpServletRequest req, boolean needToUpdate) throws ServletException, IOException {
        Part part = req.getPart("image");
        String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
        File file = new File("%s%s%s%s%s".formatted(
                FILE_PREFIX,
                File.separator,filename.hashCode() % DIRECTORIES_COUNT, File.separator, filename));

        InputStream content = part.getInputStream();
        file.getParentFile().mkdirs();
        file.createNewFile();

        FileOutputStream outputStream = new FileOutputStream(file);
        byte[] buffer = new byte[content.available()];
        content.read(buffer);
        outputStream.write(buffer);
        outputStream.close();
        if (needToUpdate) {
            return replaceImage((String) req.getAttribute("oldPhotoId"), file);
        }
        return uploadPhoto(file);
    }

    private String uploadPhoto(File file) throws IOException {
        Map<String, Object> uploadResult = cloudinary.uploader().upload(file, new HashMap<>());
        return uploadResult.get("secure_url").toString();
    }

    private String replaceImage(String oldUrl, File newImageFile) throws IOException {
        cloudinary.uploader().destroy(oldUrl, new HashMap<>());
        Map<String, Object> uploadResult = cloudinary.uploader().upload(newImageFile, new HashMap<>());
        return uploadResult.get("secure_url").toString();
    }
}
