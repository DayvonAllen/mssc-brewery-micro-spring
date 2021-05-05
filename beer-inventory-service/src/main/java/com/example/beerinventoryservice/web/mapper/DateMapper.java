package com.example.beerinventoryservice.web.mapper;

import java.sql.Timestamp;
import java.time.OffsetDateTime;

public interface DateMapper {
    OffsetDateTime asOffsetDateTime(Timestamp ts);
    Timestamp asTimestamp(OffsetDateTime offsetDateTime);
}
