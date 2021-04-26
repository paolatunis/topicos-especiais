package com.example.exercicio2;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductActivity extends AppCompatActivity {

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

        bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Product product = new Product();
                product.setName(et_name.getText().toString());
                product.setDescription(et_description.getText().toString());
                product.setPrice(Float.parseFloat(et_price.getText().toString()));
                product.setStockLevel(Integer.parseInt(et_stock.getText().toString()));
//                int stock = Integer.parseInt(et_stock.getText().toString());
//                product.setStockLevel(stock);
                product.setImageUrl(et_image.getText().toString());

                productService.create(product).enqueue(new Callback<Product>() {
                    @Override
                    public void onResponse(Call<Product> call, Response<Product> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(ProductActivity.this, "Produto cadastrado com sucesso", Toast.LENGTH_LONG).show();
                            clean();
                        }else{
                            Toast.makeText(ProductActivity.this, "Erro ao cadastrar produto", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Product> call, Throwable t) {
                        Toast.makeText(ProductActivity.this, "Erro no servidor", Toast.LENGTH_LONG).show();
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

    public void clean() {
        et_name.setText("");
        et_description.setText("");
        et_image.setText("");
        et_price.setText("");
        et_stock.setText("");
    }

}
