package com.assessment.model;

import java.util.ArrayList;
import java.util.List;

public class SqlClassBuilder
{
    private String name;

    private List<SqlField> fields;

    private List<SqlEnumBuilder> classEnums;

    public SqlClassBuilder(String name) {
        this.name = name;
        fields = new ArrayList<>();
        classEnums = new ArrayList<>();
    }

    public List<SqlEnumBuilder> getClassEnums() {
        return classEnums;
    }

    public List<SqlField> getFields() {
        return fields;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder("CREATE TABLE ").append(this.name).append("(");
        boolean firstField = true;

        for(SqlField field:fields){
            if (firstField){
                sb.append("\n").append(field.getName()).append(" ").append(field.getType());
                firstField = false;
            }else{
                sb.append("\n").append(",").append(field.getName()).append(" ").append(field.getType());
            }
        }

        for (SqlEnumBuilder eNum:this.getClassEnums()){
            sb.append("\n");
            if (fields.isEmpty())
                sb.append(eNum.toString());
            else
                sb.append(",").append(eNum.toString());
        }

        sb.append("\n);");
        return sb.toString();
    }
}