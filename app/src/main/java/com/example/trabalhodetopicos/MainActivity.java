package com.example.trabalhodetopicos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Validator.ValidationListener{
    private TextInputLayout txtName;
    private TextInputLayout txtPhone;
    private TextInputLayout txtEmail;
    private TextInputLayout txtBirth;
    private Validator validator;

    private void cleanErrors(){
        txtName.setError("");
        txtEmail.setError("");
        txtPhone.setError("");
        txtBirth.setError("");
    }

    @NotEmpty(messageResId = R.string.error_required_field)
    @Length(min = 3, max = 20, messageResId = R.string.error_name_field)
    private TextInputEditText etName;

    @NotEmpty(messageResId = R.string.error_required_field)
    @Length(min = 13, max = 13, messageResId = R.string.error_phone_field)
    private TextInputEditText etPhone;

    @NotEmpty(messageResId = R.string.error_required_field)
    @Email(messageResId = R.string.error_email_field)
    private TextInputEditText etEmail;

    @NotEmpty(messageResId = R.string.error_required_field)
    @Length(min = 10, max = 10, messageResId = R.string.error_birth_field)
    private TextInputEditText etBirth;

    private Button btRegister;
    private Button btSend;
    private Usuario usuario;
    private RadioGroup rgGender;
    private RadioButton rbGender;
    private CheckBox cbMusic;
    private CheckBox cbMovies;
    private ArrayList<Usuario> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();

        this.rgGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                rbGender = findViewById(checkedId);
            }
        });

        this.btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cleanErrors();
                validator.validate();
            }

        });


        this.btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListInfosActivity.class);
                Bundle args = new Bundle();
                args.putSerializable("USER_LIST", userList);
                intent.putExtras(args);
                startActivity(intent);
            }
        });
    }

    private void initialize() {
        this.txtName = findViewById(R.id.txt_name);
        this.txtPhone = findViewById(R.id.txt_phone);
        this.txtEmail = findViewById(R.id.txt_email);
        this.txtBirth = findViewById(R.id.txt_birth);
        this.etName = findViewById(R.id.et_name);
        this.etEmail = findViewById(R.id.et_email);
        this.etPhone = findViewById(R.id.et_phone);
        this.etBirth = findViewById(R.id.et_birth);
        this.rgGender = findViewById(R.id.rg_gender);
        this.rbGender = findViewById(R.id.rb_male);
        this.cbMusic = findViewById(R.id.cb_music);
        this.cbMovies = findViewById(R.id.cb_movies);
        this.btRegister = findViewById(R.id.bt_register);
        this.btSend = findViewById(R.id.bt_send);
        this.userList = new ArrayList<>();
        this.validator = new Validator(this);
        validator.setValidationListener(this);
    }

    private ArrayList<String> getCheckedInterests() {
        ArrayList<String> checkedInterests = new ArrayList<>();
        if(cbMusic.isChecked()){
            checkedInterests.add(cbMusic.getText().toString());
        }
        if(cbMovies.isChecked()){
            checkedInterests.add(cbMovies.getText().toString());
        }
        return checkedInterests;
    }

    @Override
    public void onValidationSucceeded() {
        usuario.setName(etName.getText().toString());
        usuario.setPhone(etPhone.getText().toString());
        usuario.setEmail(etEmail.getText().toString());
        usuario.setGender(rbGender.getText().toString());
        usuario.setInterests(getCheckedInterests());
        try {
            SimpleDateFormat df = new SimpleDateFormat(getString(R.string.date_formatter));
            usuario.setBirth(df.parse(etBirth.getText().toString()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Toast.makeText(getApplicationContext(),usuario.toString(),Toast.LENGTH_SHORT).show();
        userList.add(usuario);
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for(ValidationError e:errors) {
            View view = e.getView();
            String errorMessage = e.getCollatedErrorMessage(this);

            if(view instanceof TextInputEditText){
                switch (view.getId()){
                    case R.id.et_name:
                        txtName.setError(errorMessage);
                        break;
                    case R.id.et_phone:
                        txtPhone.setError(errorMessage);
                        break;
                    case R.id.et_email:
                        txtEmail.setError(errorMessage);
                        break;
                    case R.id.et_birth:
                        txtBirth.setError(errorMessage);
                        break;
                }
            }

        }
    }
}