package com.learning.courses.exception;

import java.io.IOException;

public class FileExtensionInvalidException extends IOException {
    public FileExtensionInvalidException(String message) {
        super(message);
    }
}
