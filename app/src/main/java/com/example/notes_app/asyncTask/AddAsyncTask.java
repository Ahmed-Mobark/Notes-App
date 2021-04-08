package com.example.notes_app.asyncTask;

import android.os.AsyncTask;

import com.example.notes_app.Room.NoteDAO;
import com.example.notes_app.Room.NotesEntity;

public class AddAsyncTask extends AsyncTask<NotesEntity,Void,Void> {
    private NoteDAO noteDAO;

    public AddAsyncTask(NoteDAO noteDAO) {
        this.noteDAO = noteDAO;
    }

    @Override
    protected Void doInBackground(NotesEntity... notesEntities) {
        noteDAO.insertNote(notesEntities[0]);
        return null;
    }
}
