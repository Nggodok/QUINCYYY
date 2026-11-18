package com.example.quincy.data.model;

import com.google.gson.annotations.SerializedName;
import java.util.Map;

public class TagModel {
    @SerializedName("id")
    private String id;

    @SerializedName("attributes")
    private Attributes attributes;

    public String getId() { return id; }

    public String getName() {
        if (attributes != null && attributes.name != null) {
            return attributes.name.get("en");
        }
        return null;
    }
    public String getGroup() {
        return attributes != null ? attributes.group : null;
    }

    static class Attributes {
        @SerializedName("name")
        Map<String, String> name;

        @SerializedName("group")
        String group;
    }
}
