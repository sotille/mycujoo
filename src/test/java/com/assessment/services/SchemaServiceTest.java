package com.assessment.services;


import com.assessment.model.SchemaWrapper;
import com.assessment.services.impl.SchemaService;
import com.assessment.services.impl.SubjectsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(JUnit4.class)
public class SchemaServiceTest {

    ISchemaService schemaService;
    ISubjectsService subjectsService;

    @Before
    public void config() {
        schemaService = new SchemaService();
        subjectsService = new SubjectsService();
    }

    @Test
    public void getSubjectsTest() {
        String[] subjects = subjectsService.getSubjects();

        assertNotNull(subjects);

        for (String subject:subjects) {
            assertNotNull(schemaService.getSchema(subject).getClass());
            assertEquals(SchemaWrapper.class,schemaService.getSchema(subject).getClass());
        }
    }
}
