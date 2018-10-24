package br.uem.iss.anesthesia.controller.binder;

import java.time.LocalDateTime;

public class LocalDateTimePropertyEditorSupport extends DateTimePropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        setValue(LocalDateTime.parse(text, formatter));
    }

    @Override
    public String getAsText() throws IllegalArgumentException {
        if (getValue() == null) {
            return null;
        }
        return formatter.format((LocalDateTime) getValue());
    }
}
