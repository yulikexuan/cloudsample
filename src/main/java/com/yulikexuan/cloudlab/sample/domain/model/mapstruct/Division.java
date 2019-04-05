//: com.yulikexuan.cloudlab.sample.domain.model.mapstruct.Division.java


package com.yulikexuan.cloudlab.sample.domain.model.mapstruct;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Builder @AllArgsConstructor
public class Division {

    private long id;
    private String name;

}///:~