package com.ntahr.common.dataaccess.mongo.exception;

public class MongoException extends Exception {

    public MongoException() {
    }

    public MongoException(Exception exception) {
        super(exception);
    }

    public MongoException(String message, Exception exception) {
        super(message, exception);
    }
}
