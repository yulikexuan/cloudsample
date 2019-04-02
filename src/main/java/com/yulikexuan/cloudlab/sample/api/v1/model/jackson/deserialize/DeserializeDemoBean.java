//: com.yulikexuan.cloudlab.sample.api.v1.model.jackson.deserialize.DeserializeDemoBean.java


package com.yulikexuan.cloudlab.sample.api.v1.model.jackson.deserialize;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.yulikexuan.cloudlab.sample.api.v1.model.jackson.serialize.SerializeDemoBean;
import lombok.*;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;


@Getter
@Setter
@NoArgsConstructor
@Builder @AllArgsConstructor
public class DeserializeDemoBean {

    @Builder.Default private long personId = 123L;

    @Builder.Default private String  name = "James Clark";

    @JsonSerialize(using = SerializeDemoBean.CustomDateSerializer.class)
    @JsonDeserialize(using = CustomDateDeserializer.class)
    @Builder.Default public OffsetDateTime activeDate = OffsetDateTime.now();

    public static class CustomDateDeserializer
            extends StdDeserializer<OffsetDateTime> {

        static final DateTimeFormatter DATE_TIME_FORMATTER =
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ");

        public CustomDateDeserializer() {
            this(null);
        }

        public CustomDateDeserializer(Class<?> vc) {
            super(vc);
        }

        @Override
        public OffsetDateTime deserialize(
                JsonParser jsonParser, DeserializationContext ctxt)
                throws IOException {

            String dateValueString = jsonParser.getText();
            return OffsetDateTime.parse(dateValueString, DATE_TIME_FORMATTER);
        }
    }

}///:~