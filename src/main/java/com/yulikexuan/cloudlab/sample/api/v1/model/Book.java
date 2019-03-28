//: com.yulikexuan.cloudlab.sample.api.v1.model.Book.java


package com.yulikexuan.cloudlab.sample.api.v1.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class Book {

    private String category;
    private String author;
    private String title;
    private double price;

}///:~