package com.example.notes_app.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.notes_app.NodesRV_Adapter;
import com.example.notes_app.R;
import com.example.notes_app.Room.NotesEntity;
import com.example.notes_app.Room.RoomFactory;
import com.example.notes_app.asyncTask.DeleteAllAsyn;
import com.example.notes_app.asyncTask.DeleteAsyncTask;
import com.example.notes_app.asyncTask.GetAsyncTask;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class HomeFragment extends Fragment {

    RecyclerView NotesRV;
    ImageView noteIv;
    ImageView deleteIv;
    NodesRV_Adapter notesAdapter;
    List<NotesEntity> notesList = new ArrayList<>();

    FloatingActionButton fab;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        NotesRV = view.findViewById(R.id.add_noteRV);
        fab = view.findViewById(R.id.add_fab);
        deleteIv=view.findViewById(R.id.Delete_AllIv);
      //   noteIv = view.findViewById(R.id.notes_iv);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        deleteIv.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                new DeleteAllAsyn(RoomFactory.getNotesDatabase(requireContext()).getNoteDAO()).execute();
             notesList.clear();
                notesAdapter.notifyDataSetChanged();

            }
        });

        //showOrHideNoteImage();
        getAllNotes();
        setUpNotesRv();
        setOnClickListeners();

    }

//    private void showOrHideNoteImage() {
//
//        if (notesList.isEmpty()){
//            NotesRV.setVisibility(View.GONE);
//            noteIv.setVisibility(View.VISIBLE);
//        } else {
//            noteIv.setVisibility(View.GONE);
//            NotesRV.setVisibility(View.VISIBLE);
//        }
//
//    }

    private void getAllNotes() {

        notesList.clear();
        try {
            notesList.addAll(new GetAsyncTask(RoomFactory.getNotesDatabase(requireContext()).getNoteDAO()).execute().get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void setOnClickListeners() {

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_addFragment);
            }
        });

    }

    private void setUpNotesRv() {


        notesAdapter = new NodesRV_Adapter(notesList, new NodesRV_Adapter.OnItemClick() {
            @Override
            public void onClick(View view, final int position) {

                setUpEditOrDeleteDialog(view, position);

            }
        });

        NotesRV.setLayoutManager(new LinearLayoutManager(requireContext()));
        NotesRV.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
        NotesRV.addItemDecoration(new DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL));
        NotesRV.setAdapter(notesAdapter);
    }

    private void setUpEditOrDeleteDialog(final View view, final int position) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(requireContext());
        dialog.setMessage("Edit or delete this note ?");

        dialog.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                deleteNote(position);

            }
        });

        dialog.setPositiveButton("Edit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                NotesEntity notesEntity = notesList.get(position);

                Bundle bundle = new Bundle();
                bundle.putSerializable("note", notesEntity);

                Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_editFragment, bundle);

            }
        });

        dialog.show();
    }




    private void deleteNote(int position) {
        NotesEntity notesEntity = notesList.get(position);
        new DeleteAsyncTask(RoomFactory.getNotesDatabase(requireContext()).getNoteDAO()).execute(notesEntity);
        notesAdapter.notifyDataSetChanged();
        getAllNotes();
        //showOrHideNoteImage();

    }
}
