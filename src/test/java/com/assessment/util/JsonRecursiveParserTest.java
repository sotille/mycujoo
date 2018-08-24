package com.assessment.util;

import com.assessment.model.SchemaWrapper;
import com.assessment.services.ISchemaService;
import com.assessment.services.ISubjectsService;
import com.assessment.services.impl.SchemaService;
import com.assessment.services.impl.SubjectsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

@RunWith(JUnit4.class)
public class JsonRecursiveParserTest {

    @Test
    public void recursiveJsonTest(){
        ISubjectsService subjectsService = new SubjectsService();

        ISchemaService schemaService = new SchemaService();

        List<SchemaWrapper> schemaWrappers = new ArrayList<>();
        String[] subjects = subjectsService.getSubjects();
        for (String subject : subjects) {
            schemaWrappers.add(schemaService.getSchema(subject));
        }

        assertEquals(new JsonRecursiveParser().convertSchemasToSqlBuilder(schemaWrappers).size(),subjects.length);

    }
}
