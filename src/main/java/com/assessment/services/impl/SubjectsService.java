package com.assessment.services.impl;

import com.assessment.constants.SystemConstants;
import com.assessment.services.BaseUrlConsumerService;
import com.assessment.services.ISubjectsService;
import com.google.gson.Gson;

public class SubjectsService extends BaseUrlConsumerService implements ISubjectsService {

    private String fetchData()  {
        return readFromBuffer(
                getUrl(SystemConstants.SUBJECTS_URL.getValue()));
    }

    private String[] parseSubjectsJson(String fetchedJson){

        if (fetchedJson == null){
            return null;
        }

        return  new Gson().fromJson(fetchedJson,String[].class);
    }

    public String[] getSubjects(){
        return parseSubjectsJson(fetchData());
    }

}
