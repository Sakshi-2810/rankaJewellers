package com.example.jewelry.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Set;

@Data
public class Stocks {
    @Id
    private Long id;
    private Set<String> items;
}
