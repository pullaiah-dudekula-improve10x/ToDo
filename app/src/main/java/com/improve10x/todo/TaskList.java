package com.improve10x.todo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TaskList implements Serializable {
    @SerializedName("_id")
    public String id;
    @SerializedName("name")
    public String task;
    public String description;
}
