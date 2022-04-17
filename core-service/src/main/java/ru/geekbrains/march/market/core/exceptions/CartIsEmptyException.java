package ru.geekbrains.march.market.core.exceptions;

public class CartIsEmptyException extends RuntimeException{
    public CartIsEmptyException(String message){
        super(message);
    }
}
