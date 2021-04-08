package com.example.notes_app.Room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDAO {
    @Query("SELECT*FROM notes")
    List<NotesEntity> getAllNotes();
@Query("Delete From notes")
void deleteAllNote();
    @Insert
    void insertNote(NotesEntity notesEntity);
    @Delete
    void deleteNote(NotesEntity notesEntity);
    @Update
    void  updateNote(NotesEntity notesEntity);
}
