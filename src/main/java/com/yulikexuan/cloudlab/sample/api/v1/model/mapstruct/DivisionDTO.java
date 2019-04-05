//: com.yulikexuan.cloudlab.sample.api.v1.model.mapstruct.DivisionDTO.java


package com.yulikexuan.cloudlab.sample.api.v1.model.mapstruct;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Builder @AllArgsConstructor
public class DivisionDTO {

    private long divisionId;
    private String divisionName;

}///:~