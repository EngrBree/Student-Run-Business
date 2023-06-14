package com.bree.ordermanagement.Service;

public class productsUnavailableExeption  extends RuntimeException{
    public productsUnavailableExeption(String message) {
        super(message);
    }
}
