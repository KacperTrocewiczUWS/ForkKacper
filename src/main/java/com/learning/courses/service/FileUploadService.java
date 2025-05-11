package com.learning.courses.service;

import com.learning.courses.exception.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class FileUploadService {

    public void SaveUploadedFile(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        int size = (int) file.getSize();


        String fileExtension = "";
        if (fileName != null && fileName.contains(".")) {
            fileExtension = fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();
        }
        boolean isJpg = "jpg".equals(fileExtension);


        BufferedImage resolution = ImageIO.read(file.getInputStream());
        int width = resolution.getWidth();
        int height = resolution.getHeight();

        boolean sizeValidation = size <= 64 * 1024;
        boolean resolutionValidation = width <= 150 && height <= 150;

        if (sizeValidation && resolutionValidation && isJpg) {
            return;
        }

        if (!sizeValidation && !resolutionValidation && !isJpg) {
            throw new AllAttributesInvalidException("All attributes are invalid");
        }
        if (sizeValidation && !resolutionValidation && !isJpg) {
            throw new FileResolutionAndExtensionInvalidException("Attributes resolution and extension are invalid");
        }
        if (!sizeValidation && resolutionValidation && !isJpg) {
            throw new FileSizeAndExtensionInvalidException("Attributes size and extension are invalid");
        }
        if (!sizeValidation && !resolutionValidation && isJpg) {
            throw new FileSizeAndResolutionInvalidException("Attributes size and resolution are invalid");
        }
        if (!sizeValidation && resolutionValidation && isJpg) {
            throw new FileSizeInvalidException("Attribute size is invalid");
        }
        if (!sizeValidation && resolutionValidation && isJpg) {
            throw new FileSizeInvalidException("Attribute size is invalid");
        }
        if (sizeValidation && !resolutionValidation && isJpg) {
            throw new FileResolutionInvalidException("Attribute resolution is invalid");
        }
        if (sizeValidation && resolutionValidation && !isJpg) {
            throw new FileExtensionInvalidException("Attribute extension is invalid");
        }
    }
}

