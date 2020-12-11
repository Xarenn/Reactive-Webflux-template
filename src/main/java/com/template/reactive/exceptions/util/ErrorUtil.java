package com.template.reactive.exceptions.util;

import com.template.reactive.exceptions.ServerException;

import java.io.Serializable;

public class ErrorUtil {

    public static class ErrorOutputMessage implements Serializable {

        public Integer code;

        public String message;

        public String errorCodeMessage;

        public ErrorOutputMessage(ServerException genericException) {
            this.code = genericException.getCode();
            this.message = genericException.getMessage();
            this.errorCodeMessage = genericException.getErrorCodeMessage();
        }

    }

    public static ErrorOutputMessage errorToMessage(ServerException genericException) {
        return new ErrorOutputMessage(genericException);
    }
}