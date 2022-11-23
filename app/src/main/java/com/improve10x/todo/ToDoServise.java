package com.improve10x.todo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ToDoServise {

    @GET("pullaiahTodo")
    Call<List<TaskList>> listTask();

    @POST("pullaiahToDo")
    Call<TaskList> createTask(@Body TaskList taskList);
}
