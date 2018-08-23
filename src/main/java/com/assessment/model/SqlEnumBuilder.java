package com.assessment.model;

public class SqlEnumBuilder
{
    private final String name;

    private final String[] symbols;

    public SqlEnumBuilder(final String name, final String[] symbols)
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