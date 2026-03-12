package org.skypro.skyshop.model.error;

public class ShopError {
    private final String message;
    private final int code;

    public ShopError(String message, int code) {
        this.message = message;
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static ShopError newError(int code, String message) {
        return new ShopError(message, code);
    }
}
