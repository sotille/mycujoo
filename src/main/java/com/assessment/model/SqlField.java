package com.assessment.model;

public class SqlField {

    private final String name;

    private final String type;

    private final boolean nullable;

    public SqlField(String name, String type, boolean nullable) {
        this.name = name;
        this.type = type;
        this.nullable = nullable;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public boolean isNullable() {
        return nullable;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(name).append(" ").append(type);

        if (!nullable)
            sb.append(" NOT NULL");

        return sb.toString();
    }
}
