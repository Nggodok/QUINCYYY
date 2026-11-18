package com.example.quincy.data.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class TagResponse {
    @SerializedName("data")
    private List<TagModel> data;

    public List<TagModel> getData() { return data; }
}
