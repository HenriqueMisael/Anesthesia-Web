package br.uem.iss.anesthesia.controller.binder;

import java.beans.PropertyEditorSupport;
import java.time.format.DateTimeFormatter;

import static java.time.format.DateTimeFormatter.ofPattern;

public abstract class DateTimePropertyEditorSupport extends PropertyEditorSupport {

    protected DateTimeFormatter formatter = ofPattern("dd/MM/yyyy");

    @Override
    public abstract void setAsText(String text) throws IllegalArgumentException;

    @Override
    public abstract String getAsText() throws IllegalArgumentException;
}
