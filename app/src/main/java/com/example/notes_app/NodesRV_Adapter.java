package com.example.notes_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notes_app.Room.NotesEntity;

import java.util.List;

public class NodesRV_Adapter extends RecyclerView.Adapter<NodesRV_Adapter.NotesViewHolder> {

    List<NotesEntity> notesList;
    OnItemClick onItemClick;

    public interface OnItemClick {
        void onClick(View view, int position);
    }


    public NodesRV_Adapter(List<NotesEntity> notesList, OnItemClick onItemClick) {
        this.notesList = notesList;
        this.onItemClick=onItemClick;
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view,parent,false);
        return new NotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, final int position) {
        NotesEntity note=notesList.get(position);
        holder.Hedder_tv.setText(note.getTitle());
        holder.Details_tv.setText(note.getDetails());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClick.onClick(view, position);
            }
        });



    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

    class NotesViewHolder extends RecyclerView.ViewHolder{
        TextView Hedder_tv;
        TextView Details_tv;

        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);
            Hedder_tv=itemView.findViewById(R.id.Hedder_tv);
            Details_tv=itemView.findViewById(R.id.Details_tv);


        }
    }

}