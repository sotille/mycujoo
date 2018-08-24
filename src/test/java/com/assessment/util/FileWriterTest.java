package com.assessment.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;
import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class FileWriterTest {

    @Test
    public void verifyFileWriterTest(){
        FileWriter.writeSqlFile(new ArrayList<>());
        File file = new File("output.sql");
        assertTrue(file.exists());
    }

    @Test
    public void verifyNullArrayFileWriterTest(){
        FileWriter.writeSqlFile(null);
        File file = new File("output.sql");
        assertTrue(file.exists());
    }

}
