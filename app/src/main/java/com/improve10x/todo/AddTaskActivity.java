package com.improve10x.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddTaskActivity extends AppCompatActivity {
    public TaskList taskList;
    public Button updateBtn;
    public Button taskAddBtn;
    public EditText taskTxt;
    public EditText descriptionTxt;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        taskAddBtn = findViewById(R.id.task_add_btn);
        taskTxt = findViewById(R.id.task_txt);
        descriptionTxt = findViewById(R.id.description_txt);
        updateBtn = findViewById(R.id.update_btn);
        if(getIntent().hasExtra("taskList")) {
            getSupportActionBar().setTitle("Edit Task");
           taskList = (TaskList) getIntent().getSerializableExtra("taskList");
           updateBtn.setVisibility(View.VISIBLE);
           taskAddBtn.setVisibility(View.GONE);
           showData();
           handleUpdateBtn();
        } else {
            getSupportActionBar().setTitle("Add Task");
            taskAddBtn.setVisibility(View.VISIBLE);
            updateBtn.setVisibility(View.GONE);
            addBtn();
        }


    }
    public void handleUpdateBtn() {
        updateBtn.setOnClickListener(view -> {
            String taskName = taskTxt.getText().toString();
            String description = descriptionTxt.getText().toString();
            upDateTask(taskList.id, taskName, description);

        });
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
        taskAddBtn.setOnClickListener(view -> {
            String taskName = taskTxt.getText().toString();
            String description = descriptionTxt.getText().toString();
            addTask(taskName,description);
        });
    }
    public void showData() {
        taskTxt.setText(taskList.task);
        descriptionTxt.setText(taskList.description);
    }
    public void upDateTask(String id, String taskName, String description) {
        TaskList taskList = new TaskList();
        taskList.task = taskName;
        taskList.description = description;

        ToDoApi toDoApi = new ToDoApi();
        ToDoServise toDoServise = toDoApi.createToDoServise();
        Call<Void>call = toDoServise.updateTask(id,taskList);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(AddTaskActivity.this, "Successfull update Task", Toast.LENGTH_SHORT).show();
                finish();

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(AddTaskActivity.this, "Failed to update task", Toast.LENGTH_SHORT).show();

            }
        });

    }

}