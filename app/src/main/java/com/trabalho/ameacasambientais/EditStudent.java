package com.trabalho.ameacasambientais;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EditStudent extends AppCompatActivity {
    StudentSQLiteDatabase db;
    EditText txtEndereco, txtData, txtDesc;
    Student current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);

        txtEndereco = findViewById(R.id.editTextPersonName5);
        txtData = findViewById(R.id.editTextPersonName8);
        txtDesc = findViewById(R.id.editTextPersonName9);

        db = new StudentSQLiteDatabase(
                getBaseContext());

        Long id = getIntent().getLongExtra("ID",0);
        current = db.getStudent(id);

        txtEndereco.setText(current.getEndereco());
        txtData.setText(current.getData());
        txtDesc.setText(current.getDesc());
    }

    public void updateStudent(View v){
        current.setEndereco(txtEndereco.getText().toString());
        current.setData(txtData.getText().toString());
        current.setDesc(txtDesc.getText().toString());
        db.updateStudent(current);
        finish();
    }
}