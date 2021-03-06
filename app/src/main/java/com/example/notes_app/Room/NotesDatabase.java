package com.example.notes_app.Room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {NotesEntity.class},version = 1,exportSchema = false)
public abstract class NotesDatabase extends RoomDatabase {
    public abstract NoteDAO getNoteDAO();
}
