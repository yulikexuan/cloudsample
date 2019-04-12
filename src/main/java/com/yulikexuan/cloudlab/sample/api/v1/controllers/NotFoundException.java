//: com.yulikexuan.cloudlab.sample.api.v1.controllers.NotFoundException.java


package com.yulikexuan.cloudlab.sample.api.v1.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Content Not Found")
public class NotFoundException extends RuntimeException {

    private final String resourceName;

    public NotFoundException(String resourceName) {
        super();
        this.resourceName = resourceName;
    }

    @Override
    public String toString() {
        return String.format("%1$s : %2$s", this.getClass().getSimpleName(),
                this.resourceName);
    }

}///:~