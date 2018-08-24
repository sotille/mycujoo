package com.assessment;

import com.assessment.model.SchemaWrapper;
import com.assessment.model.SqlClassBuilder;
import com.assessment.services.ISchemaService;
import com.assessment.services.ISubjectsService;
import com.assessment.services.impl.SchemaService;
import com.assessment.services.impl.SubjectsService;
import com.assessment.util.FileWriter;
import com.assessment.util.JsonRecursiveParser;
import com.google.gson.JsonIOException;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MycujooApplicationRunner {

    public static void main(String[] args) {
        ISubjectsService subjectsService = new SubjectsService();
        ISchemaService schemaService = new SchemaService();
        List<SchemaWrapper> schemaWrappers = new ArrayList<>();

        try {
            for (String subject : subjectsService.getSubjects()) {
                schemaWrappers.add(schemaService.getSchema(subject));
            }

            List<SqlClassBuilder> sqlClassBuilders = new JsonRecursiveParser().convertSchemasToSqlBuilder(schemaWrappers);
            FileWriter.writeSqlFile(sqlClassBuilders);

        } catch (IOException | JsonIOException | JSONException e) {
            System.out.println("Something got wrong look at the log file to see what happened");
            e.printStackTrace();
        }
    }
}
