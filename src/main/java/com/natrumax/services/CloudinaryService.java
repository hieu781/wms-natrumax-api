package com.natrumax.services;

import com.cloudinary.Cloudinary;
import com.natrumax.dto.response.CloudinaryResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService {
    @Autowired
    private Cloudinary cloudinary;

    @Transactional
    public CloudinaryResponse uploadFile(final MultipartFile file, final String fileName) {
        try {
            final Map result   = this.cloudinary.uploader()
                    .upload(file.getBytes(),
                            Map.of("public_id",
                                    "wms-natrumax/product/"
                                            + fileName));
            final String url      = (String) result.get("secure_url");
            final String publicId = (String) result.get("public_id");
            CloudinaryResponse response = new CloudinaryResponse();
            response.setPublicId(publicId);
            response.setUrl(url);
            return response;

        } catch (final Exception e) {
            throw new IllegalArgumentException("Failed to upload file");
        }
    }

    public void deleteFile(final String publicId) {
        try {
            this.cloudinary.uploader().destroy(publicId, Map.of());
        } catch (final Exception e) {
            throw new IllegalArgumentException("Failed to delete file");
        }

    }
}
