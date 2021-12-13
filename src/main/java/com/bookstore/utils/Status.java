package com.bookstore.utils;

public enum Status {
    Active(1),
    Canceled(2),
    Delivered(3);

    public final int code;

    Status(int code){
        this.code = code;
    }

    public int getValue(){
        return code;
    }
}
