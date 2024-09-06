package com.trabalho.ameacasambientais;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddStudent extends AppCompatActivity {

    StudentSQLiteDatabase db;
    EditText txtEndereco, txtData, txtDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        txtEndereco = findViewById(R.id.editTextPersonName5);
        txtData = findViewById(R.id.editTextPersonName8);
        txtDesc = findViewById(R.id.editTextPersonName9);

        db = new StudentSQLiteDatabase(
                getBaseContext());
    }

    public void addStudent(View v) {
        Student s = new Student();
        s.setEndereco(txtEndereco.getText().toString());
        s.setData(txtData.getText().toString());
        s.setDesc(txtDesc.getText().toString());

        db.addStudent(s);
        finish();
    }
}