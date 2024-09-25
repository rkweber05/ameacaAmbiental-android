package com.trabalho.estudantesfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    public static final String AMEACAS_KEY = "ameacas";
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference root = database.getReference();
    DatabaseReference ameacas = root.child(AMEACAS_KEY);
    FirebaseListAdapter<Ameacas> adapter;
    ListView listAmeacas;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listAmeacas = findViewById(R.id.lista);
        adapter = new FirebaseListAdapter<Ameacas>(this, Ameacas.class,
                R.layout.activity_student_adapter, ameacas) {
            @Override
            protected void populateView(View v, Ameacas model, int position) {
                TextView Desc = v.findViewById(R.id.editTextPersonName9);
                TextView Data = v.findViewById(R.id.editTextPersonName8);
                ImageView image = v.findViewById(R.id.image);
                if (Desc != null) {
                    Desc.setText(model.getDescricao());
                }

                if (Data != null) {
                    Data.setText(model.getData());
                }
                if (model.getImagem() != null){
                    byte imageData[] = Base64.decode(model.getImagem(), Base64.DEFAULT);
                    Bitmap img = BitmapFactory.decodeByteArray(imageData, 0, imageData.length);
                    image.setImageBitmap(img);
                }
            }
        };

        listAmeacas.setAdapter(adapter);
        listAmeacas.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                DatabaseReference item = adapter.getRef(position);
                item.removeValue();
                return false;
            }
        });

        listAmeacas.setOnItemClickListener((parent, view, position, id) -> {
            DatabaseReference item = adapter.getRef(position);
            editStudent(item.getKey(), adapter.getItem(position));
        });
    }

    public void addStudent(View v) {
        Intent it = new Intent(getBaseContext(), AddStudent.class);
        startActivity(it);
    }

    public void editStudent(String key, Ameacas a) {
        Intent it = new Intent(getBaseContext(), EditStudent.class);
        it.putExtra("KEY", key);
        it.putExtra("AME", a);
        startActivity(it);
    }
}