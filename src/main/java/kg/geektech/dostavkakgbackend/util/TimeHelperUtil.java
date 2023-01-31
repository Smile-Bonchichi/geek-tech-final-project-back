package kg.geektech.dostavkakgbackend.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class TimeHelperUtil {
    public static final String DATE_TIME_FORMAT = "dd.MM.yyyy HH:mm";
    public static final String DATE_FORMAT = "dd.MM.yyyy";
    public static final String DATE_FORMAT_V2 = "yyyy-MM-dd";
    public static final String DATE_FORMAT_V3 = "dd-MM-yyyy";
    public static final String DATE_STRING = "ddMMyyyy";

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);
    public static final DateTimeFormatter DATE_FORMATTER_V2 = DateTimeFormatter.ofPattern(DATE_FORMAT_V2);
    public static final DateTimeFormatter DATE_FORMATTER_V3 = DateTimeFormatter.ofPattern(DATE_FORMAT_V3);
    public static final DateTimeFormatter DATE_STRING_FORMATTER = DateTimeFormatter.ofPattern(DATE_STRING);

    public static Date convertToDateViaInstant(LocalDate dateToConvert) {
        return Date.from(
                dateToConvert.atStartOfDay()
                        .atZone(ZoneId.systemDefault())
                        .toInstant()
        );
    }

    public static Date convertToDateViaInstant(LocalDateTime dateToConvert) {
        return Date
                .from(
                        dateToConvert.atZone(ZoneId.systemDefault())
                                .toInstant()
                );
    }
}
