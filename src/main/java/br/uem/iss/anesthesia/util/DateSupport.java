package br.uem.iss.anesthesia.util;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class DateSupport {

    public static final String DATE_FORMAT = "dd/MM/yy";
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT + " HH:mm");
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT);

    public String format(LocalDateTime dateTime) {
        return dateTimeFormatter.format(dateTime);
    }

    public String format(LocalDate date) {
        return dateFormatter.format(date);
    }
}
