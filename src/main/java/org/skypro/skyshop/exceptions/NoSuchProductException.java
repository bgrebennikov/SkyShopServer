package org.skypro.skyshop.exceptions;

public class NoSuchProductException  extends RuntimeException{

    public NoSuchProductException() {
        super("No such product");
    }

}
