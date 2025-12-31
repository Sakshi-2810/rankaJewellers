package com.example.jewelry.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
public class Invoice {
    @Id
    private String invoiceNumber;
    private String date;

    private Customer customer;
    private Rates rates;
    private List<Item> items;
    private Summary summary;

    // Getters & Setters

    @Data
    public static class Customer {
        private String name;
        private String phone;
        private String address;
    }

    @Data
    public static class Rates {
        private double gold;
        private double silver;
    }

    @Data
    public static class Item {
        private String description;
        private String metal;
        private double purity;
        private double weight;
        private double metalValue;
        private double makingCharge;
        private double total;
    }

    @Data
    public static class Summary {
        private double subtotal;
        private double cgst;
        private double sgst;
        private double grandTotal;
        private double cashReceived;
        private double balanceDue;
        private String totalInWords;
    }
}
