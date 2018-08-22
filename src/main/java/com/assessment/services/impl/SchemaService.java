package com.assessment.services.impl;

import com.assessment.constants.SystemConstants;
import com.assessment.services.BaseUrlConsumerService;
import com.assessment.services.ISchemaService;

public class SchemaService extends BaseUrlConsumerService implements ISchemaService {

    public String fetchData(String subjectName)  {
        String dataToValidate = readFromBuffer(
                getUrl(SystemConstants.SCHEMAS_URL.getValue()+subjectName+".json"));
        return dataToValidate;
    }
}
