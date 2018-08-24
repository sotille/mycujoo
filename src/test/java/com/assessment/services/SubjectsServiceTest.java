package com.assessment.services;

import com.assessment.services.impl.SubjectsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class SubjectsServiceTest {

    ISubjectsService subjectsService;

    @Before
    public void config() {
        subjectsService = new SubjectsService();
    }

    @Test
    public void getSubjectsTest() {
        assertTrue(new Integer(subjectsService.getSubjects().length) > 0);
    }
}
