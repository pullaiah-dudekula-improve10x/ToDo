package com.improve10x.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        addBtn();

    }

    private void addTask(String name, String description) {
        TaskList taskList = new TaskList();
        taskList.task = name;
        taskList.description = description;

        ToDoApi toDoApi = new ToDoApi();
        ToDoServise toDoServise = toDoApi.createToDoServise();
        Call<TaskList> call = toDoServise.createTask(taskList);
        call.enqueue(new Callback<TaskList>() {
            @Override
            public void onResponse(Call<TaskList> call, Response<TaskList> response) {
                Toast.makeText(AddTaskActivity.this, "Succesfull", Toast.LENGTH_SHORT).show();
                finish();

            }

            @Override
            public void onFailure(Call<TaskList> call, Throwable t) {
                Toast.makeText(AddTaskActivity.this, "Fail to load data", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void addBtn() {
        Button addBtn = findViewById(R.id.add_btn);
        addBtn.setOnClickListener(view -> {
            EditText taskTxt = findViewById(R.id.task_txt);
            String taskName = taskTxt.getText().toString();
            EditText descriptionTxt = findViewById(R.id.description_txt);
            String description = descriptionTxt.getText().toString();
            addTask(taskName,description);
        });
    }

}