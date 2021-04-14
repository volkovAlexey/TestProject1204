package com.myapp.models;

import org.json.JSONObject;

public class Rule {
    private String key;
    private String condition;
    private Object val;

    public Rule(JSONObject jsonObject){
        this.key = jsonObject.getString("key");
        this.condition = jsonObject.getString("condition");
        this.val = jsonObject.get("val");
    }

    public String getKey() {
        return key;
    }

    public String getCondition() {
        return condition;
    }

    public Object getVal() {
        return val;
    }
}
