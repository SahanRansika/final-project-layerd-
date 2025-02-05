package com.ijse.gdse.bookstore.entity;

import com.ijse.gdse.bookstore.dto.OrderDetailsDTO;
import lombok.*;

import java.sql.Date;
import java.util.ArrayList;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Orders {
    private String orderId;
    private String customerId;
    private Date orderDate;
    private ArrayList<OrderDetailsDTO> orderDetailsDTOS;
}
