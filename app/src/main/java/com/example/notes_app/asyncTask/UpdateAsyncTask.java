package com.example.notes_app.asyncTask;

import android.os.AsyncTask;

import com.example.notes_app.Room.NoteDAO;
import com.example.notes_app.Room.NotesEntity;

public class UpdateAsyncTask extends AsyncTask<NotesEntity,Void,Void> {
    private NoteDAO noteDAO;

    public UpdateAsyncTask(NoteDAO noteDAO) {
        this.noteDAO = noteDAO;
    }


    @Override
    protected Void doInBackground(NotesEntity... notesEntities) {
        noteDAO.updateNote(notesEntities[0]);
        return null;
    }
}
