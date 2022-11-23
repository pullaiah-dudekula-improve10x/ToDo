package com.improve10x.todo;

import com.google.gson.annotations.SerializedName;

public class TaskList {
    @SerializedName("_id")
    public String id;
    @SerializedName("name")
    public String task;
    public String description;
}
