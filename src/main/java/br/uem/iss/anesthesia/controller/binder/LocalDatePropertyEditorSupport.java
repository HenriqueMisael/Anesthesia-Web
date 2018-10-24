package br.uem.iss.anesthesia.controller.binder;

import java.time.LocalDate;

public class LocalDatePropertyEditorSupport extends DateTimePropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (!text.isEmpty()) {
            setValue(LocalDate.parse(text, formatter));
        }
    }

    @Override
    public String getAsText() throws IllegalArgumentException {
        if (getValue() == null) {
            return null;
        }
        return formatter.format((LocalDate) getValue());
    }
}
