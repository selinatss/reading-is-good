package com.bookstore.response;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@Data
public class RestResponse<T> {
    private T data;
    private HttpStatus status;
    private List<Message> messages = new ArrayList<>();

    public RestResponse(T data, HttpStatus status){
        this.data = data;
        this.status = status;
    }

    public RestResponse(HttpStatus status, Message message){
        this.data = data;
        this.status = status;
    }

    public static <T> RestResponse<T> of(T t, HttpStatus status){
        RestResponse<T> restResponse = new RestResponse<>(t, status);
        return restResponse;
    }

    public static RestResponse of(HttpStatus status, Message message){
        RestResponse restResponse = new RestResponse(status, message);
        return restResponse;
    }

    public static <T> RestResponse<T> of(T t, HttpStatus status, Message message){
        RestResponse<T> restResponse = new RestResponse<>(t, status);
        restResponse.getMessages().add(message);
        return restResponse;
    }
}
