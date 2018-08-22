package com.assessment.controller;

import com.assessment.model.SchemaWrapper;
import com.assessment.services.impl.SchemaService;
import com.google.gson.Gson;

public class SchemaController {

    SchemaService schemaService;

    public SchemaController(SchemaService schemaService) {
        this.schemaService= schemaService;
    }

    public SchemaWrapper getSchema(String subject){
        return parseSchemasJson(schemaService.fetchData(subject));
    }

    private SchemaWrapper parseSchemasJson(String fetchedJson){

        if (fetchedJson == null){
            return null;
        }

        SchemaWrapper schemaWrapper =  new Gson().fromJson(fetchedJson,SchemaWrapper.class);

        return schemaWrapper;
    }
}
