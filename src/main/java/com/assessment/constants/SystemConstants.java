package com.assessment.constants;

public enum SystemConstants {

    SUBJECTS_URL("https://s3-eu-west-1.amazonaws.com/mycujoo-assignments/be-assignment/subjects.json"),
    SCHEMAS_URL("https://s3-eu-west-1.amazonaws.com/mycujoo-assignments/be-assignment/");

    final String value;

    SystemConstants(String value) {
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}
