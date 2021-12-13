package com.bookstore.utils;

import com.bookstore.response.Message;

public class Constant {
    public static final Message successMessage = new Message("Process successful", 200);
    public static final Message errorMessage = new Message("An error occured during process", 500);
}
