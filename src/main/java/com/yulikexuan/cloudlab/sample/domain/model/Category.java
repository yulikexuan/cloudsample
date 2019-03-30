//: com.yulikexuan.cloudlab.sample.domain.model.Category.java


package com.yulikexuan.cloudlab.sample.domain.model;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdDate;

    @Builder
    public Category(Long id, String name, Timestamp createdDate) {
        this.id = id;
        this.name = name;
        this.createdDate = createdDate;
    }

}///:~