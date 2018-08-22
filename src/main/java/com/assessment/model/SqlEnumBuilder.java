package com.assessment.model;

public class SqlEnumBuilder
{
    private String name;

    private String[] symbols;

    public SqlEnumBuilder(String name, String[] symbols)
    {
        this.name = name;
        this.symbols = symbols;
    }

    @Override
    public String toString()
    {
        boolean firstSymbol = true;

        StringBuilder sb = new StringBuilder(this.name +" ENUM(");

        for(String key:symbols){
            if (firstSymbol){
                firstSymbol = false;
                sb.append("'").append(key).append("'");
            }
            else
                sb.append(",'").append(key).append("'");
        }
        sb.append(")");
        return sb.toString();
    }
}