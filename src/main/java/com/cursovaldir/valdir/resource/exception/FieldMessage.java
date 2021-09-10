package com.cursovaldir.valdir.resource.exception;

import java.io.Serializable;

public class FieldMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    private String fieldName;
    private String messsage;

    public FieldMessage() {
        super();
    }

    public FieldMessage(String fieldName, String messsage) {
        super();
        this.fieldName = fieldName;
        this.messsage = messsage;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getMesssage() {
        return messsage;
    }

    public void setMesssage(String messsage) {
        this.messsage = messsage;
    }

}
