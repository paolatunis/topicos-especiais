package com.example.trabalhodetopicos;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;

public class DetailsActivity extends AppCompatActivity {
    private Bundle bundle;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        initializeComponents();

        Toast.makeText(this, formatDetails(),Toast.LENGTH_LONG).show();
    }

    private void initializeComponents() {
        this.bundle = getIntent().getExtras();
        this.usuario = (Usuario) bundle.getSerializable("USER_OBJECT");
    }

    private String formatDetails() {
        SimpleDateFormat dateFormatter = new SimpleDateFormat(getString(R.string.date_formatter));
        String interests = "";
        for(String item:usuario.getInterests()){
            interests += ",\n" + item;
        }
        return usuario.getName() + ",\n" +
                usuario.getPhone() + ",\n" +
                usuario.getEmail() + ",\n" +
                dateFormatter.format(usuario.getBirth()) + ",\n" +
                usuario.getGender() +
                interests;
    }

}

