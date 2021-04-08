package com.example.notes_app.asyncTask;

import android.os.AsyncTask;

import com.example.notes_app.Room.NoteDAO;
import com.example.notes_app.Room.NotesEntity;

import java.util.List;

public class  GetAsyncTask extends AsyncTask<Void,Void, List<NotesEntity>> {
    private NoteDAO noteDAO;

    public GetAsyncTask(NoteDAO noteDAO) {
        this.noteDAO = noteDAO;
    }

    @Override
    protected List<NotesEntity> doInBackground(Void... voids) {
        return  noteDAO.getAllNotes();
    }
}
