package com.trabalho.estudantesfirebase;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;

public class AddStudent extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference root = database.getReference();
    DatabaseReference ameacas = root.child(MainActivity.AMEACAS_KEY);
    EditText End, Data, Desc;
    public static final int CAMERA_CALL = 1022;
    Bitmap bmp;
    ImageView image;
    Boolean hasImage = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        End = findViewById(R.id.editTextPersonName5);
        Data = findViewById(R.id.editTextPersonName8);
        Desc = findViewById(R.id.editTextPersonName9);
        image = findViewById(R.id.image);
    }

    public void novaAmeaca(View v) {
        Ameacas a = new Ameacas();
        a.setEndereco(End.getText().toString());
        a.setData(Data.getText().toString());
        a.setDescricao(Desc.getText().toString());

        if (hasImage){
            String bmpEncoded = loadImage();
            hasImage = false;
            a.setImagem(bmpEncoded);
        }

        String key = ameacas.push().getKey();
        ameacas.child(key).setValue(a);
        finish();
    }

    public String loadImage(){
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, byteOut);
        return Base64.encodeToString(byteOut.toByteArray(), Base64.DEFAULT);
    }

    public void TakePicture(View v){
        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camera, CAMERA_CALL);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_CALL && resultCode == RESULT_OK){
            bmp = (Bitmap) data.getExtras().get("data");
            image.setImageBitmap(bmp);
            hasImage = true;
        }
    }
}