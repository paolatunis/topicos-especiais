package com.example.exercicio2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormProduct extends AppCompatActivity {
    private EditText et_name;
    private EditText et_description;
    private EditText et_image;
    private EditText et_price;
    private EditText et_stock;
    private Button bt_delete;
    private Button bt_save;
    private ProductService productService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        initialize();
        Bundle dados = getIntent().getExtras();
        Product product = (Product) dados.getSerializable("selecionado");

        et_name.setText(product.getName());
        et_description.setText(product.getDescription());
        et_image.setText(product.getImageUrl());
        et_price.setText(String.valueOf(product.getPrice()));
        et_stock.setText(String.valueOf(product.getStockLevel()));

        bt_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productService.delete(product.getId()).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            Intent intentDelete = new Intent(FormProduct.this, MainActivity.class);
                            startActivity(intentDelete);
                        } else {
                            Toast.makeText(FormProduct.this, "Erro ao deletar", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(FormProduct.this, "Erro no servidor", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });


        bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                product.setName(et_name.getText().toString());
                product.setDescription(et_description.getText().toString());
                product.setPrice(Float.parseFloat(et_price.getText().toString()));
                product.setStockLevel(Integer.parseInt(et_stock.getText().toString()));
                product.setImageUrl(et_image.getText().toString());

                productService.update(product.getId(), product).enqueue(new Callback<Product>() {
                    @Override
                    public void onResponse(Call<Product> call, Response<Product> response) {
                        if(response.isSuccessful()){
                            Intent intent = new Intent(FormProduct.this, MainActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(FormProduct.this, "Erro ao editar", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Product> call, Throwable t) {
                        Toast.makeText(FormProduct.this, "Erro no servidor", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }


    public void initialize() {
        et_name = findViewById(R.id.et_name);
        et_description = findViewById(R.id.et_description);
        et_image = findViewById(R.id.et_image);
        et_price = findViewById(R.id.et_price);
        et_stock = findViewById(R.id.et_stock);
        bt_delete = findViewById(R.id.bt_delete);
        bt_save = findViewById(R.id.bt_save);
        productService = RetrofitUtils.retrofit.create(ProductService.class);
    }

}
