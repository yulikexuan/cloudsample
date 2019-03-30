//: com.yulikexuan.cloudlab.sample.api.v1.mappers.DateMapper.java


package com.yulikexuan.cloudlab.sample.api.v1.mappers;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

public class DateMapper {

    public OffsetDateTime timestampToOffsetDateTime(Timestamp timestamp) {

        return Optional.ofNullable(timestamp)
                .map(ts -> {
                    LocalDateTime ldt = ts.toLocalDateTime();
                    return OffsetDateTime.of(ldt.getYear(),
                            ldt.getMonthValue(),
                            ldt.getDayOfMonth(),
                            ldt.getHour(),
                            ldt.getMinute(),
                            ldt.getSecond(),
                            ldt.getNano(),
                            OffsetDateTime.now().getOffset());})
                .orElse(null);
    }

    public Timestamp offsetDateTimeToTimestamp(OffsetDateTime offsetDateTime) {

        return Optional.ofNullable(offsetDateTime)
                .map(offsetDateTime1 -> Timestamp.valueOf(offsetDateTime
                        .atZoneSameInstant(ZoneOffset.UTC).toLocalDateTime()))
                .orElse(null);
    }

}///:~