package com.trabalho.ameacasambientais;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Window window = this.getWindow();
        window.setStatusBarColor(this.getResources().getColor(android.R.color.white));
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        ListView lista = findViewById(R.id.lista);
        List<Student> student = todosOsStudents();
        ArrayAdapter<Student> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, student);
        lista.setAdapter(adapter);
        lista.setItemsCanFocus(true);

        button = findViewById(R.id.novaAmeaca);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });
    }

    public List<Student> todosOsStudents() {
        return new ArrayList<>(Arrays.asList(
                new Student("Corte Irregular de Vegetação Nativa.", "02/04/2023"),
                new Student("Pesca predatória com rede de arrasto.", "02/05/2023"),
                new Student("Descarte irregular de lixo hospitalar.", "02/07/2023"),
                new Student("Derrame químico em sistema de esgoto pluviral.", "02/08/2023")
        ));
    }

    private void openActivity2() {
        Intent intent = new Intent(this, AddStudent.class);
        startActivity(intent);
    }
}