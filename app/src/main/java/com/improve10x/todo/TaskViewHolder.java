package com.improve10x.todo;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TaskViewHolder extends RecyclerView.ViewHolder {
    TextView textTxt;
    TextView descriptionTxt;
    ImageButton deleteIb;
    public TaskViewHolder(@NonNull View itemView) {
        super(itemView);
        textTxt = itemView.findViewById(R.id.text_txt);
        descriptionTxt = itemView.findViewById(R.id.description_txt);
        deleteIb = itemView.findViewById(R.id.delete_ib);
    }
}
