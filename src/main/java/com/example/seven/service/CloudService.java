package com.example.seven.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@AllArgsConstructor
public class CloudService {

    private final Cloudinary cloudinary;

    public String getUrl(MultipartFile img){
        try {
            Map uploadResult = cloudinary.uploader().upload(img.getBytes(), ObjectUtils.emptyMap());
            String publicId = uploadResult.get("public_id").toString();
            return cloudinary.url().secure(true)
                    .publicId(publicId)
                    .generate();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
