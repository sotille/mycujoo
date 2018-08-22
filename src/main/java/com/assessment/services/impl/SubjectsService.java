package com.assessment.services.impl;

import com.assessment.constants.SystemConstants;
import com.assessment.services.BaseUrlConsumerService;
import com.assessment.services.ISubjectsService;

public class SubjectsService extends BaseUrlConsumerService implements ISubjectsService {

    public String fetchData()  {
        String subjects = readFromBuffer(
                getUrl(SystemConstants.SUBJECTS_URL.getValue()));

        return subjects;
    }
}
