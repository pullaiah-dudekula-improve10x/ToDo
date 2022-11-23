package com.improve10x.todo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskViewHolder> {
    public List<TaskList> taskLists;
    public OnItemActionListener onItemActionListener;

    public void setData(List<TaskList>tasks) {
        taskLists = tasks;
        notifyDataSetChanged();
    }
    public void setOnItemActionListener(OnItemActionListener actionListener) {
        onItemActionListener = actionListener;
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
        holder.textTxt.setText(taskList.task);
        holder.descriptionTxt.setText(taskList.description);
        holder.deleteIb.setOnClickListener(view -> {
            onItemActionListener.onDelete(taskList.id);
        });
        holder.itemView.setOnClickListener(view -> {
            onItemActionListener.onEdit(taskList);
        });

    }

    @Override
    public int getItemCount() {
        return taskLists.size();
    }
}
