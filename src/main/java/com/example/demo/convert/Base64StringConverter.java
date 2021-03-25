package com.example.demo.convert;

import org.springframework.util.StringUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Converter
public class Base64StringConverter implements AttributeConverter<String, byte[]> {

    @Override
    public byte[] convertToDatabaseColumn(String s) {
        if(StringUtils.isEmpty(s)){
            return null;
        }
        return Base64.getEncoder().encode(s.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public String convertToEntityAttribute(byte[] bytes) {
        if (bytes != null && bytes.length > 0) {
            return new String(Base64.getDecoder().decode(bytes));
        }
        return null;
    }
}