package com.myapp.controllers;

import com.myapp.models.Project;
import com.myapp.models.Rule;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@Controller
@RequestMapping("/q")
public class ServiceController {

    private File file;
    private String resourceJson;

    public ServiceController() {
        try {
            this.file = ResourceUtils.getFile("classpath:rules.json");
            this.resourceJson = new String(Files.readAllBytes(file.toPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostMapping
    public String redirect(@RequestBody String jsonString) {
        boolean equalRequestData = equalRequestData(jsonString);
        if (equalRequestData) {
            return "";
        }
        return "data is invalid";
    }

    private boolean equalRequestData(String jsonString) {
        JSONObject jsonObject = new JSONObject(jsonString);
        JSONArray jsonArray = jsonObject.getJSONArray("projects");
        JSONObject rulesJsonObject = new JSONObject(resourceJson);
        JSONArray rules = rulesJsonObject.getJSONArray("rules");
        JSONArray arrayRules = null;
        for (int i = 0; i < rules.length(); i++) {
            JSONObject obj = rules.getJSONObject(i);
            arrayRules = obj.getJSONArray("conditions");
        }
        Rule category = new Rule(arrayRules.getJSONObject(0));
        Rule stage = new Rule(arrayRules.getJSONObject(1));
        Rule created_at = new Rule(arrayRules.getJSONObject(2));

        for (int i = 0; i < jsonArray.length(); i++) {

            Project project = new Project(jsonArray.getJSONObject(i));
            List<Object> categories = project.getCategories();

            if (!categories.contains(category.getVal())) {
                return false;
            }
            if (!project.getStage().equals(stage.getVal())) {
                return false;
            }
            int val = (int) created_at.getVal();
            if (project.getCreated_at() > val) {
                return false;
            }
        }
        return true;
    }
}
