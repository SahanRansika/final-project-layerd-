package com.ijse.gdse.bookstore.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDetails {
    private String orderId;
    private String bookId;
    private int quantity;
    private double price;
}
