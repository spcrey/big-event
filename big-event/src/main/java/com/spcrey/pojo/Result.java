package com.spcrey.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Result<T> {

    private Integer code;

    private String message;
    
    private T data;

    public static <E> Result<E> success(E data) {
        return new Result<>(0, "operation successful", data);
    }

    public static<E> Result<E> success() {
        return new Result<>(0, "operation successful", null);
    }

    public static<E> Result<E> error(String message) {
        return new Result<>(1, message, null);
    }
}
