package com.ijse.gdse.bookstore.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    private String colID;
    private String colName;
    private String colEmail;
    private String colPosition;
    private String colPassword;
}
