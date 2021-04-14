package com.myapp.models;

import org.json.JSONObject;

import java.util.List;

public class Project {
    private int id;
    private String name;
    private String description;
    private String stage;
    private List<Object> categories;
    private int created_at;
    private int modified_at;

    public Project(JSONObject jsonObject) {
        this.id = jsonObject.getInt("id");
        this.name = jsonObject.getString("name");
        this.description = jsonObject.getString("description");
        this.stage = jsonObject.getString("stage");
        this.categories = jsonObject.getJSONArray("categories").toList();
        this.created_at = jsonObject.getInt("created_at");
        this.modified_at = jsonObject.getInt("modified_at");
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getStage() {
        return stage;
    }

    public List<Object> getCategories() {
        return categories;
    }

    public int getCreated_at() {
        return created_at;
    }

    public int getModified_at() {
        return modified_at;
    }
}
