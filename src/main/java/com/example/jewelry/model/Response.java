package com.example.jewelry.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Response {
    private Object data;
    private String message;
}
