//: com.yulikexuan.cloudlab.sample.api.v1.model.jackson.deserialize.DeserializeDemoBean.java


package com.yulikexuan.cloudlab.sample.api.v1.model.jackson.deserialize;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;


public class DeserializeDemoBean {

    private long personId = 123L;

    private String  name = "James Clark";

    @JsonDeserialize(using = CustomDateDeserializer.class)
    public OffsetDateTime activeDate;

    public static class CustomDateDeserializer extends StdDeserializer<OffsetDateTime> {

        static final DateTimeFormatter DATE_TIME_FORMATTER =
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ");

        public CustomDateDeserializer() {
            this(null);
        }

        public CustomDateDeserializer(Class<?> vc) {
            super(vc);
        }

        @Override
        public OffsetDateTime deserialize(JsonParser jsonParser, DeserializationContext ctxt)
                throws IOException, JsonProcessingException {
            String date = jsonParser.getText();
            return (OffsetDateTime) DATE_TIME_FORMATTER.parse(date);
        }
    }

}///:~