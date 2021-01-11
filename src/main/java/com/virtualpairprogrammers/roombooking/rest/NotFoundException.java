package com.virtualpairprogrammers.roombooking.rest;

public class NotFoundException extends RuntimeException {
    private static final long serialVersionUID = 3288565779639216395L;

    public NotFoundException(final String type, final Object id) {
        super(type + " " + id + " could not be found");
    }
}
