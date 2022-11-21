package com.improve10x.todo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskViewHolder> {
    public ArrayList<TaskList>taskLists;
    public void setData(ArrayList<TaskList>tasks) {
        taskLists = tasks;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item,parent,false);
        TaskViewHolder taskViewHolder = new TaskViewHolder(view);
        return taskViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        TaskList taskList = taskLists.get(position);
        holder.textTxt.setText(taskList.taskList);
        holder.descriptionTxt.setText(taskList.description);

    }

    @Override
    public int getItemCount() {
        return taskLists.size();
    }
}
