package com.assessment.services.impl;

import com.assessment.constants.SystemConstants;
import com.assessment.model.SchemaWrapper;
import com.assessment.services.BaseUrlConsumerService;
import com.assessment.services.ISchemaService;
import com.google.gson.Gson;

public class SchemaService extends BaseUrlConsumerService implements ISchemaService {

    public SchemaWrapper getSchema(String subject){
        return parseSchemasJson(fetchData(subject));
    }

    private String fetchData(String subjectName)  {
        return readFromBuffer(
                getUrl(SystemConstants.SCHEMAS_URL.getValue()+subjectName+".json"));
    }

    private SchemaWrapper parseSchemasJson(String fetchedJson){

        if (fetchedJson == null){
            return null;
        }

        return new Gson().fromJson(fetchedJson,SchemaWrapper.class);
    }
}
