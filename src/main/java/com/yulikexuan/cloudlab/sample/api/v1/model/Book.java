//: com.yulikexuan.cloudlab.sample.api.v1.model.Book.java


package com.yulikexuan.cloudlab.sample.api.v1.model;


import lombok.*;


@Data
@NoArgsConstructor
public class Book {

    private String category;
    private String author;
    private String title;
    private double price;

}///:~