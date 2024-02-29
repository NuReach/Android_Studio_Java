package com.nnureach.lkw_androidapp;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class CreatePost extends AppCompatActivity {
    EditText etPostTitle;
    EditText etPostBody;
    Button btnPostSubmit;
    ImageView uploadImg;
    Uri imagePath;
    String imageUriAcessToken;
    FirebaseAuth firebaseAuth;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference ;

    FirebaseFirestore firebaseFirestore;

    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);
        uploadImg = findViewById(R.id.updateImg);
        etPostTitle = findViewById(R.id.etPostTitle);
        etPostBody = findViewById(R.id.etPostBody);
        btnPostSubmit = findViewById(R.id.btnPostSubmit);

    }

}