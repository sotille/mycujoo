package com.assessment;

import com.assessment.controller.SchemaController;
import com.assessment.controller.SubjectsController;
import com.assessment.model.SchemaWrapper;
import com.assessment.model.SqlClassBuilder;
import com.assessment.services.impl.SchemaService;
import com.assessment.services.impl.SubjectsService;
import com.assessment.util.FileWriter;
import com.assessment.util.JsonRecursiveParser;

import java.util.ArrayList;
import java.util.List;

public class MycujooApplicationRunner {

    public static void main(String[] args) {

        SubjectsController subscriptionController = new SubjectsController(new SubjectsService());
        SchemaController schemaController = new SchemaController(new SchemaService());

        List<SchemaWrapper> schemaWrappers = new ArrayList<>();

        for (String subject : subscriptionController.getSubjects()) {
            schemaWrappers.add(schemaController.getSchema(subject));
        }

        List<SqlClassBuilder> sqlClassBuilders = new JsonRecursiveParser().convertSchemasToSqlBuilder(schemaWrappers);
        FileWriter.writeSqlFile(sqlClassBuilders);
    }
}
