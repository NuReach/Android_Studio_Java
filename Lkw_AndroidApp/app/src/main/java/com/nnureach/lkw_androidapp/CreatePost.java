package com.nnureach.lkw_androidapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.UUID;

public class CreatePost extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;
    EditText titleEditText;
    EditText bodyEditText;
    Button uploadButton;
    ImageView imageView;

    TextView chooseImageButton;

    private Uri imageUri;
    private StorageReference storageReference;
    private FirebaseFirestore firestore;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);
        chooseImageButton = findViewById(R.id.btnUploadImg);
        titleEditText = findViewById(R.id.etPostTitle);
        bodyEditText = findViewById(R.id.etPostBody);
        uploadButton = findViewById(R.id.btnPostSubmit);
        imageView = findViewById(R.id.imgView);

        storageReference = FirebaseStorage.getInstance().getReference("uploads");
        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        chooseImageButton.setOnClickListener(v -> openFileChooser());

        uploadButton.setOnClickListener(v -> uploadImage());

    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            imageView.setImageURI(imageUri);
        }
    }

    private void uploadImage() {
        if (imageUri != null) {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Uploading...");
            progressDialog.show();

            // Create a unique filename for the image using the current time
            String imageName = System.currentTimeMillis() + "." + getFileExtension(imageUri);

            final StorageReference fileReference = storageReference.child(imageName);

            // Upload image to Firebase Storage
            fileReference.putFile(imageUri).addOnSuccessListener(taskSnapshot -> {
                fileReference.getDownloadUrl().addOnSuccessListener(uri -> {
                    // Image uploaded successfully, now save the image details to Firestore
                    String title = titleEditText.getText().toString().trim();
                    String body = bodyEditText.getText().toString().trim();
                    String imageUrl = uri.toString();
                    String userId = auth.getCurrentUser().getUid();
                    UUID uuid = UUID.randomUUID();
                    // Convert UUID to a String
                    String id = uuid.toString();
                    Upload upload = new Upload(title, body, imageUrl,userId);

                    firestore.collection("uploads")
                            .document(id)
                            .set(upload)
                            .addOnSuccessListener(unused -> {
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(), "Upload successful", Toast.LENGTH_SHORT).show();
                                finish();
                            }).addOnFailureListener(e -> {
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(), "Upload failed", Toast.LENGTH_SHORT).show();
                            });
                }).addOnFailureListener(e -> {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                });
            });

        }else {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Uploading...");
            progressDialog.show();
            String title = titleEditText.getText().toString().trim();
            String body = bodyEditText.getText().toString().trim();
            String imageUrl = "https://wiki.lordofthecraft.net/images/1/1e/noimg.png";
            String userId = auth.getCurrentUser().getUid();
            UUID uuid = UUID.randomUUID();
            // Convert UUID to a String
            String id = uuid.toString();
            Upload upload = new Upload(title, body, imageUrl,userId);

            firestore.collection("uploads")
                    .document(id)
                    .set(upload)
                    .addOnSuccessListener(unused -> {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Upload successful", Toast.LENGTH_SHORT).show();
                        finish();
                    }).addOnFailureListener(e -> {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Upload failed", Toast.LENGTH_SHORT).show();
                    });
        }
    }
    private String getFileExtension(Uri uri) {
        // Get the file extension of the selected image
        return MimeTypeMap.getSingleton().getExtensionFromMimeType(getContentResolver().getType(uri));
    }

}