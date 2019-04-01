//: com.yulikexuan.cloudlab.sample.api.v1.model.jackson.serialize.SerializeDemoBean.java


package com.yulikexuan.cloudlab.sample.api.v1.model.jackson.serialize;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import lombok.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;


@Getter
@Setter
@NoArgsConstructor
@Builder @AllArgsConstructor
public class SerializeDemoBean {

    @Builder.Default public long personId = 123L;

    @Builder.Default public String  name = "James Clark";

    @JsonSerialize(using = CustomDateSerializer.class)
    @Builder.Default public OffsetDateTime activeDate = OffsetDateTime.now();

    static class CustomDateSerializer extends StdSerializer<OffsetDateTime> {

        private static final DateTimeFormatter dateTimeFormatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ");

        public CustomDateSerializer() {
            this(null);
        }

        public CustomDateSerializer(Class<OffsetDateTime> t) {
            super(t);
        }

        @Override
        public void serialize(OffsetDateTime offsetDateTime,
                              JsonGenerator jsonGenerator,
                              SerializerProvider serializerProvider)
                throws IOException {

            jsonGenerator.writeString(seralize(offsetDateTime));
        }

        public static String seralize(OffsetDateTime offsetDateTime) {
            return dateTimeFormatter.format(offsetDateTime);
        }
    }

}///:~