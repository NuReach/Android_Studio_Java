package com.nnureach.lkw_androidapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class ChatFragment extends Fragment {

    private FirebaseFirestore firestore;
    private FirebaseAuth auth;


    private RecyclerView recyclerView;
    private PostAdapter postAdapter;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.chatlayout, container, false);
        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        recyclerView = view.findViewById(R.id.recycleView);
        // Read data from Firestore
        readDataFromFirestore();

        FloatingActionButton floatingActionButton = view.findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(v -> {
            startActivity(new Intent(view.getContext(),CreatePost.class));
        });

        return view;
    }

    private void readDataFromFirestore() {
        // Assuming "posts" is the name of your collection
        firestore.collection("uploads")
                .get()
                .addOnCompleteListener(task -> {
                    List<Upload> posts = new ArrayList<>();
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            // Access each document
                            String title = document.getString("title");
                            String body = document.getString("body");
                            String imageUrl = document.getString("imageUrl");
                            String userId = document.getString("userId");
                            // Use the retrieved data as needed
                            Upload post = new Upload(title, body, imageUrl, userId);
                            posts.add(post);
                        }

                        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
                        postAdapter = new PostAdapter(this.getContext(), posts);
                        recyclerView.setAdapter(postAdapter);


                    } else {
                        Log.e("FirestoreData", "Error getting documents: ", task.getException());
                    }
                });
    }


}
