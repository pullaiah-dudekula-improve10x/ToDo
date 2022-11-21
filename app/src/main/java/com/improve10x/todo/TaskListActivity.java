package com.improve10x.todo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;

public class TaskListActivity extends AppCompatActivity {
    public ArrayList<TaskList>taskLists;
    public RecyclerView taskListRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);
        getSupportActionBar().setTitle("Task List");
        setUpData();
        setUpTaskListRv();
        setUpAdd();
    }

    public void setUpAdd() {
        Button addBtn = findViewById(R.id.add_btn);
        addBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, AddTaskActivity.class);
            startActivity(intent);
        });

    }

    public void setUpTaskListRv() {
        taskListRv = findViewById(R.id.tasklist_rv);
        taskListRv.setLayoutManager(new LinearLayoutManager(this));
        TaskAdapter taskAdapter = new TaskAdapter();
        taskAdapter.setData(taskLists);
        taskListRv.setAdapter(taskAdapter);
    }

    private void setUpData() {
        taskLists = new ArrayList<>();
        TaskList vegetables = new TaskList();
        vegetables.taskList = "Get vegetables";
        vegetables.description = "for 1 week";
        taskLists.add(vegetables);

        TaskList news = new TaskList();
        news.taskList = "Reading News";
        news.description = "Explore politics,filmy and sports news";
        taskLists.add(news);

        TaskList lunch = new TaskList();
        lunch.taskList = "Prepare Lunch";
        lunch.description = "Biryani and Raitha.Yummyyyyy";
        taskLists.add(lunch);

        TaskList breakFast = new TaskList();
        breakFast.taskList = "Have Breakfast";
        breakFast.description = "Healthy breakfast for a better morning";
        taskLists.add(breakFast);
    }
}