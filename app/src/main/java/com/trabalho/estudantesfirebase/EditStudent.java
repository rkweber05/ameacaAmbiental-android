package com.trabalho.estudantesfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditStudent extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference root = database.getReference();
    DatabaseReference ameacas = root.child(MainActivity.AMEACAS_KEY);
    EditText End, Data, Desc;
    Ameacas current;
    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);

        End = findViewById(R.id.editTextPersonName5);
        Data = findViewById(R.id.editTextPersonName8);
        Desc = findViewById(R.id.editTextPersonName9);

        key = getIntent().getStringExtra("KEY");
        current = (Ameacas) getIntent().getSerializableExtra("AME");

        End.setText(current.getEndereco());
        Data.setText(current.getData());
        Desc.setText(current.getDescricao());
    }

    public void editaAmeaca(View v) {
        current.setEndereco(End.getText().toString());
        current.setData(Data.getText().toString());
        current.setDescricao(Desc.getText().toString());
        ameacas.child(key).setValue(current);
        finish();
    }
}