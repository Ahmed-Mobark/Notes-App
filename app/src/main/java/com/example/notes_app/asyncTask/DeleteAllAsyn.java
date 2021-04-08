package com.example.notes_app.asyncTask;

import android.os.AsyncTask;

import com.example.notes_app.Room.NoteDAO;

public class  DeleteAllAsyn extends AsyncTask <Void,Void,Void>{
 private NoteDAO noteDAO;

    public DeleteAllAsyn(NoteDAO noteDAO) {
        this.noteDAO = noteDAO;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        noteDAO.deleteAllNote();
        return null;
    }
}
