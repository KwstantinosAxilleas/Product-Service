package com.example.productservice.Exceptions;

public class DuplicateException extends RuntimeException{

    public DuplicateException(String name, Class<?> entity){
        super("The product with name '" + name + "' already exists in our records, use different product name.");
    }
}