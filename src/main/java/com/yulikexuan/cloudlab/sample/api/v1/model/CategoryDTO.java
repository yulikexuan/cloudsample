//: com.yulikexuan.cloudlab.sample.api.v1.model.CategoryDTO.java


package com.yulikexuan.cloudlab.sample.api.v1.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;


@Data
@NoArgsConstructor
public class CategoryDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("createDate")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ssZ", shape=JsonFormat.Shape.STRING)
    private OffsetDateTime createdDate = null;

    @Builder
    public CategoryDTO(Long id, String name, OffsetDateTime createdDate) {
        this.id = id;
        this.name = name;
        this.createdDate = createdDate;
    }

}///:~