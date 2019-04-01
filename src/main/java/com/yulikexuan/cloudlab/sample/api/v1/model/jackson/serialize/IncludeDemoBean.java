//: com.yulikexuan.cloudlab.sample.api.v1.model.jackson.serialize.IncludeDemoBean.java


package com.yulikexuan.cloudlab.sample.api.v1.model.jackson.serialize;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;


@Builder @AllArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class IncludeDemoBean {

    @Builder.Default private long personId = 123L;
    @Builder.Default private String name = null;
    @Builder.Default private String sinCode = "";

}///:~