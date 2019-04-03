//: com.yulikexuan.cloudlab.sample.api.v1.model.jackson.general.FormatDemoBean.java


package com.yulikexuan.cloudlab.sample.api.v1.model.jackson.general;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.OffsetDateTime;
import java.time.ZoneId;


@Getter
@Setter
@NoArgsConstructor
@Builder @AllArgsConstructor
public class FormatDemoBean {

    @JsonProperty("Id")
    @Builder.Default
    private long personId = 17L;

    @JsonProperty("Name")
    @Builder.Default
    private String name = "James Bond";


    @JsonProperty("ActiveDate")
    /*
     * "objectMapper.registerModule(new JavaTimeModule());" is needed
     * Also, not works for deserialization
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd'T'hh:mm:ssZ")
    @Builder.Default
    public OffsetDateTime activeDate = OffsetDateTime.now();

}///:~