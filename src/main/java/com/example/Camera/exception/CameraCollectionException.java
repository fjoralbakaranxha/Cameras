package com.example.Camera.exception;

public class CameraCollectionException extends Exception {
    public CameraCollectionException(String message) {
        super(message);
    }

    public static String NotFoundException(String id) {
        return "Camera with " + id + "not found";
    }

    public static String IpAlreadyExists() {
        return "Camera with given ip already exists!";
    }



}

