package com.improve10x.todo;

public interface OnItemActionListener {

    void onDelete(String taskId);
    void onEdit (TaskList task);
}
