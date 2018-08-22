package com.assessment.util;

import com.assessment.model.SqlClassBuilder;

import java.io.*;
import java.util.List;

public class FileWriter {

    public static void  writeSqlFile(List<SqlClassBuilder> sqlClassBuilderList){
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("output.sql"), "utf-8"))) {

            StringBuilder finalCode = new StringBuilder();

            boolean firstTable = true;
            for (SqlClassBuilder sqlClassBuilder : sqlClassBuilderList){
                if (firstTable){
                    finalCode.append(sqlClassBuilder.toString());
                    firstTable = false;
                }else{
                    finalCode.append("\n");
                    finalCode.append(sqlClassBuilder.toString());
                }
            }

            writer.write(finalCode.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
