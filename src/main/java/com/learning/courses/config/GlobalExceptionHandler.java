package com.learning.courses.config;

import com.learning.courses.exception.*;
import com.learning.courses.exception.FileResolutionAndExtensionInvalidException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final ExceptionMapper exceptionMapper;

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleEntityNotFoundException(EntityNotFoundException entityNotFoundException) {
        log.error("Entity not found: name: {}, id: {}", entityNotFoundException.getEntityName(), entityNotFoundException.getIdentifier());

        return ResponseEntity.status(HttpStatus.NOT_FOUND.value())
                .body(exceptionMapper.toErrorResponseDTO(entityNotFoundException));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponseDTO> handleDataIntegrityViolationException(DataIntegrityViolationException integrityViolationException) {
        log.error(integrityViolationException.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
                .body(exceptionMapper.toErrorResponseDTO(integrityViolationException));
    }

    @ExceptionHandler(InvalidRoleException.class)
    public ResponseEntity<ErrorResponseDTO> handleInvalidRoleException(InvalidRoleException invalidRoleException) {
        log.error(invalidRoleException.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
                .body(exceptionMapper.toErrorResponseDTO(invalidRoleException));
    }

    @ExceptionHandler(OngoingCourseModifyException.class)
    public ResponseEntity<ErrorResponseDTO> handleOngoingCourseModifyException(OngoingCourseModifyException courseModifyException) {
        log.error(courseModifyException.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
                .body(exceptionMapper.toErrorResponseDTO(courseModifyException));
    }

    @ExceptionHandler(OngoingCourseJoinException.class)
    public ResponseEntity<ErrorResponseDTO> handleOngoingCourseJoinException(OngoingCourseJoinException courseJoinException) {
        log.error(courseJoinException.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
                .body(exceptionMapper.toErrorResponseDTO(courseJoinException));
    }

    @ExceptionHandler(EmptyCourseException.class)
    public ResponseEntity<ErrorResponseDTO> handleEmptyCourseException(EmptyCourseException emptyCourseException) {
        log.error(emptyCourseException.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
                .body(exceptionMapper.toErrorResponseDTO(emptyCourseException));
    }

    @ExceptionHandler(InactiveCourseModifyException.class)
    public ResponseEntity<ErrorResponseDTO> handleInactiveCourseModifyException(InactiveCourseModifyException inactiveCourseModifyException) {
        log.error(inactiveCourseModifyException.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
                .body(exceptionMapper.toErrorResponseDTO(inactiveCourseModifyException));
    }

    @ExceptionHandler(MissingGradeException.class)
    public ResponseEntity<ErrorResponseDTO> handleMissingGradeException(MissingGradeException missingGradeException) {
        log.error(missingGradeException.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
                .body(exceptionMapper.toErrorResponseDTO(missingGradeException));
    }

    //added exceptions
    @ExceptionHandler(AllAttributesInvalidException.class)
    public ResponseEntity<ErrorResponseDTO> handleAllAttributesInvalidException(AllAttributesInvalidException allAttributesInvalidException) {
        log.error(allAttributesInvalidException.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
                .body(exceptionMapper.toErrorResponseDTO(allAttributesInvalidException));
    }

    //only one
    @ExceptionHandler(FileResolutionInvalidException.class)
    public ResponseEntity<ErrorResponseDTO>
    handleFileResolutionInvalidException(FileResolutionInvalidException

                                                 fileResolutionInvalidException) {
        log.error(fileResolutionInvalidException.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
                .body(exceptionMapper.toErrorResponseDTO(fileResolutionInvalidException));
    }

    @ExceptionHandler(FileSizeInvalidException.class)
    public ResponseEntity<ErrorResponseDTO> handleFileSizeInvalidException(FileSizeInvalidException fileSizeInvalidException) {
        log.error(fileSizeInvalidException.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
                .body(exceptionMapper.toErrorResponseDTO(fileSizeInvalidException));
    }

    @ExceptionHandler(FileExtensionInvalidException.class)
    public ResponseEntity<ErrorResponseDTO> handleFileExtensionInvalidException(FileExtensionInvalidException fileExtensionInvalidException) {
        log.error(fileExtensionInvalidException.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
                .body(exceptionMapper.toErrorResponseDTO(fileExtensionInvalidException));
    }

    //two errors
    @ExceptionHandler(FileSizeAndExtensionInvalidException.class)
    public ResponseEntity<ErrorResponseDTO> handleFileSizeAndExtensionInvalidException(FileSizeAndExtensionInvalidException fileSizeAndExtensionInvalidException) {
        log.error(fileSizeAndExtensionInvalidException.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())

                .body(exceptionMapper.toErrorResponseDTO(fileSizeAndExtensionInvalidException));
    }

    @ExceptionHandler(FileSizeAndResolutionInvalidException.class)
    public ResponseEntity<ErrorResponseDTO> handleFileSizeAndResolutionInvalidException(FileSizeAndResolutionInvalidException fileSizeAndResolutionInvalidException) {
        log.error(fileSizeAndResolutionInvalidException.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())

                .body(exceptionMapper.toErrorResponseDTO(fileSizeAndResolutionInvalidException));
    }

    @ExceptionHandler(FileResolutionAndExtensionInvalidException.class)
    public ResponseEntity<ErrorResponseDTO> handleFileResolutionAndExtensionInvalidException(FileResolutionAndExtensionInvalidException fileResolutionAndExtensionInvalidException) {
        log.error(fileResolutionAndExtensionInvalidException.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
                .body(exceptionMapper.toErrorResponseDTO(fileResolutionAndExtensionInvalidException));
    }

}
