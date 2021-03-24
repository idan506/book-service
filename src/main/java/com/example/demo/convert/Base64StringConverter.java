package com.example.demo.convert;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Base64;

@Converter
public class Base64StringConverter implements AttributeConverter<String, byte[]> {

    @Override
    public byte[] convertToDatabaseColumn(String s) {
        return Base64.getMimeDecoder().decode(s);
    }

    @Override
    public String convertToEntityAttribute(byte[] bytes) {
        return Base64.getMimeEncoder().encodeToString(bytes);
    }
}