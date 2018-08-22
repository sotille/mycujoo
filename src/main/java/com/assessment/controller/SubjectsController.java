package com.assessment.controller;

import com.assessment.services.ISubjectsService;
import com.google.gson.Gson;

public class SubjectsController {

    ISubjectsService subjectsService;

    public SubjectsController(ISubjectsService subjectsService) {
        this.subjectsService = subjectsService;
    }

    public String[] getSubjects(){
        return parseSubjectsJson(subjectsService.fetchData());
    }

    private String[] parseSubjectsJson(String fetchedJson){

        if (fetchedJson == null){
            return null;
        }

        String subjects[] =  new Gson().fromJson(fetchedJson,String[].class);
        return subjects;
    }
}
