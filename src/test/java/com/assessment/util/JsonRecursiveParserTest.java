package com.assessment.util;

import com.assessment.model.SchemaWrapper;
import com.assessment.model.SqlClassBuilder;
import com.assessment.services.ISchemaService;
import com.assessment.services.ISubjectsService;
import com.assessment.services.impl.SchemaService;
import com.assessment.services.impl.SubjectsService;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

@RunWith(JUnit4.class)
public class JsonRecursiveParserTest {

    @Test
    public void recursiveJsonTest() throws JSONException {
        ISubjectsService subjectsService = new SubjectsService();
        ISchemaService schemaService = new SchemaService();

        List<SchemaWrapper> schemaWrappers = new ArrayList<>();
        String[] subjects = subjectsService.getSubjects();
        for (String subject : subjects) {
            schemaWrappers.add(schemaService.getSchema(subject));
        }

        List<SqlClassBuilder> sqlClassBuilders = new JsonRecursiveParser().convertSchemasToSqlBuilder(schemaWrappers);
        assertEquals(sqlClassBuilders.size(),subjects.length);

        if (!sqlClassBuilders.isEmpty())
            for (int i=0;i<subjects.length;i++){
                assertEquals(schemaWrappers.get(i).getSubject(),subjects[i]);
            }
    }
}
