package com.example.exercicio2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    public ListView lvProduct;
    public List<Product> products;
    public ProductService productService;
    public Button btAdd;
    public ArrayAdapter<Product> adapterProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.lvProduct = findViewById(R.id.lv_product);
        this.btAdd = findViewById(R.id.bt_add);
        this.productService = RetrofitUtils.retrofit.create(ProductService.class);

        this.productService.getAll().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if(response.isSuccessful()){
                    products = response.body();
                    adapterProduct = new ArrayAdapter<Product>(MainActivity.this, android.R.layout.simple_list_item_1, products);
                    lvProduct.setAdapter(adapterProduct);
                }else{
                    Toast.makeText(MainActivity.this, "Erro ao selecionar dados", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Erro no servidor", Toast.LENGTH_LONG).show();
            }
        });

        this.btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAdd = new Intent(MainActivity.this, ProductActivity.class);
                startActivity(intentAdd);
            }
        });

        this.lvProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Product selectedProduct = products.get(position);

                Intent intentDelete = new Intent(MainActivity.this, FormProduct.class);
                intentDelete.putExtra("selecionado", selectedProduct);
                startActivity(intentDelete);
            }
        });
    }
}

