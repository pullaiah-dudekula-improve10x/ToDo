package com.improve10x.todo;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ToDoApi {
    public ToDoServise createToDoServise() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://crudcrud.com/api/479dd07f8c1d482e9219f7dcb48e25f4/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ToDoServise toDoServise = retrofit.create(ToDoServise.class);
        return toDoServise;

    }
}





