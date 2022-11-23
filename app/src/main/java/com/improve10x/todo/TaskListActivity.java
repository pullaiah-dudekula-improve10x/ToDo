package com.improve10x.todo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TaskListActivity extends AppCompatActivity {
    public List<TaskList>taskLists = new ArrayList<>();
    public RecyclerView taskListRv;
    public TaskAdapter taskAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);
        getSupportActionBar().setTitle("Task List");
        setUpTaskListRv();
        setUpAdd();


    }

    public void editTask(TaskList taskList) {
        Intent intent = new Intent(this,AddTaskActivity.class);
        intent.putExtra("taskList",taskList);
        startActivity(intent);

    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchData();
    }

    public void fetchData() {
        ToDoApi toDoApi = new ToDoApi();
        ToDoServise toDoServise = toDoApi.createToDoServise();
        Call<List<TaskList>> Call = toDoServise.listTask();
        Call.enqueue(new Callback<List<TaskList>>() {
            @Override
            public void onResponse(retrofit2.Call<List<TaskList>> call, Response<List<TaskList>> response) {
                List<TaskList>taskLists = response.body();
                taskAdapter.setData(taskLists);

            }

            @Override
            public void onFailure(retrofit2.Call<List<TaskList>> call, Throwable t) {
                Toast.makeText(TaskListActivity.this, "Failed to fetch Data", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void setUpAdd() {
        Button addBtn = findViewById(R.id.add_btn);
        addBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, AddTaskActivity.class);
            startActivity(intent);
        });

    }
    public void delete(String id) {
        ToDoApi toDoApi = new ToDoApi();
        ToDoServise toDoServise = toDoApi.createToDoServise();
        Call<Void> call = toDoServise.onDelete(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(TaskListActivity.this, "Success", Toast.LENGTH_SHORT).show();
                fetchData();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(TaskListActivity.this, "Failed", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void setUpTaskListRv() {
        taskListRv = findViewById(R.id.tasklist_rv);
       taskListRv.setLayoutManager(new LinearLayoutManager(this));
        taskAdapter = new TaskAdapter();
        taskAdapter.setData(taskLists);
        taskAdapter.setOnItemActionListener(new OnItemActionListener() {
            @Override
            public void onDelete(String taskId) {
                delete(taskId);

            }

            @Override
            public void onEdit(TaskList task) {
                editTask(task);

            }
        });
        taskListRv.setAdapter(taskAdapter);
    }

}