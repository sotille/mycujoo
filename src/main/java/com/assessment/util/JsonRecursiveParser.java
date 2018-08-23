package com.assessment.util;

import com.assessment.model.SchemaWrapper;
import com.assessment.model.SqlClassBuilder;
import com.assessment.model.SqlEnumBuilder;
import com.assessment.model.SqlField;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JsonRecursiveParser {

    private final List<SqlClassBuilder> sqlClassBuilderList = new ArrayList<>();

    public List<SqlClassBuilder> convertSchemasToSqlBuilder(List<SchemaWrapper> schemaWrappers){
        for (SchemaWrapper schemaWrapper : schemaWrappers){
            JSONObject rootElement;
            try {
                rootElement = new JSONObject(schemaWrapper.getSchema());
                createTableElement(rootElement);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return this.sqlClassBuilderList;
    }

    private void createTableElement(JSONObject jsonTable){
        try {
            JSONArray fields = jsonTable.getJSONArray("fields");

            SqlClassBuilder tableToCreate = new SqlClassBuilder(jsonTable.get("name").toString());

            loopThroughJson(fields,tableToCreate);

            sqlClassBuilderList.add(tableToCreate);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private static void loopThroughJson(Object input, SqlClassBuilder tableToCreate) throws JSONException {
        if (input instanceof JSONArray) {
            for (int i = 0; i < ((JSONArray) input).length(); i++) {
                if (((JSONArray) input).get(i) instanceof JSONObject){
                    JSONObject jsonObject = (JSONObject) ((JSONArray) input).get(i);
                    verifyJsonField(jsonObject,tableToCreate);
                }
            }
        }
    }

    private static void verifyJsonField(JSONObject jsonObject, SqlClassBuilder tableToCreate) throws JSONException {
        if (isField(jsonObject)){
            if (!(jsonObject.get("type") instanceof  JSONArray)){
                tableToCreate.getFields().add(getJsonAsField(jsonObject));
            }else{
                if (jsonObject.getJSONArray("type").get(0).equals("null")
                        || jsonObject.getJSONArray("type").get(1).equals("null")) {
                    if (jsonObject.getJSONArray("type").get(1) instanceof JSONObject){
                        tableToCreate.getFields().add(getJsonAsNullableField(jsonObject.getJSONArray("type")));
                    }else{
                        tableToCreate.getFields().add(getJsonAsNullableField(jsonObject));
                    }
                }
            }
        }
        else{
            if (isEnum(jsonObject)){
                tableToCreate.getClassEnums().add(getAsEnum(jsonObject));
            }else{
                if (isRecordOrArray(jsonObject)){
                    tableToCreate.getFields().add(getRecordOrArrayAsField(jsonObject));
                }
                else{
                    Iterator<?> keys = jsonObject.keys();
                    while (keys.hasNext()) {
                        String key = (String) keys.next();
                        if ((jsonObject.get(key) instanceof JSONArray) && (jsonObject.get(key) instanceof JSONObject))
                            loopThroughJson(jsonObject.get(key), tableToCreate);
                    }
                }
            }
        }
    }

    private static SqlField getJsonAsNullableField(JSONObject jsonObject) {
        SqlField field = null;
        try {
            String type = ((JSONArray) jsonObject.get("type")).get(0).toString().equals("null")
                    ? ((JSONArray) jsonObject.get("type")).get(1).toString() :
                    ((JSONArray) jsonObject.get("type")).get(0).toString();

            field = new SqlField(jsonObject.get("name").toString(), type,true);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return field;
    }

    private static SqlField getJsonAsNullableField(JSONArray jsonArray) {
        SqlField field = null;
        try {
            JSONObject subtype = jsonArray.get(0).toString().equals("null")
                    ? (JSONObject) jsonArray.get(1) :
                    (JSONObject) jsonArray.get(0);

            String type = (subtype.get("type").toString().equals("array") || subtype.get("type").toString().equals("record")) ? "varchar" : subtype.get("type").toString();

            if (isField(subtype)){
                field = new SqlField(subtype.get("name").toString(), type,true);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return field;
    }


    private static SqlField getJsonAsField(JSONObject jsonObject) {
        try {
            return new SqlField(jsonObject.get("name").toString(),jsonObject.get("type").toString(), false);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static SqlField getRecordOrArrayAsField(JSONObject jsonObject) {
        try {
            String name = ((JSONObject) jsonObject.get("type")).has("name") ?
                    ((JSONObject) jsonObject.get("type")).get("name").toString() :
                    jsonObject.get("name").toString();

            return new SqlField(name,"varchar", false);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static boolean isRecordOrArray(JSONObject jsonObject) {
        try {
            return ((JSONObject) jsonObject.get("type")).get("type").toString().equals("record") ||
                    ((JSONObject) jsonObject.get("type")).get("type").toString().equals("array");
        }catch (Exception e){
            return false;
        }
    }

    private static boolean isField(JSONObject jsonObject) {
        try {
            ((JSONObject) jsonObject.get("type")).get("type").toString();
            return false;
        }catch (Exception e){
            return true;
        }
    }

    private static boolean isEnum(JSONObject jsonObject) {
        try {
            return ((JSONObject) jsonObject.get("type")).get("type").toString().equals("enum");
        }catch (Exception e){
            return false;
        }
    }

    private static SqlEnumBuilder getAsEnum(JSONObject jsonObject) {
        try {
            String name  = ( (JSONObject) jsonObject.get("type")).get("name").toString();
            JSONArray symbols  = ( (JSONObject) jsonObject.get("type")).getJSONArray("symbols");
            String[] symbolsArray = new String[symbols.length()];

            for (int i = 0; i < symbols.length(); i++) {
                symbolsArray[i] = symbols.get(i).toString();
            }

            return new SqlEnumBuilder (name, symbolsArray);
        }catch (Exception e){
            return null;
        }
    }
}
