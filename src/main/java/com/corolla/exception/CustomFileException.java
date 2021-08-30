package com.corolla.exception;

/**
 * <pre>
 * @description:
 * @copyright:
 * @author: kan.feng
 * @version: 1.0
 * @date: 2021-08-30
 * @time: 20:16
 * </pre>
 */
public class CustomFileException extends RuntimeException {

    public CustomFileException() {
    }

    public CustomFileException(String message) {
        super(message);
    }

    public CustomFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomFileException(Throwable cause) {
        super(cause);
    }
}
