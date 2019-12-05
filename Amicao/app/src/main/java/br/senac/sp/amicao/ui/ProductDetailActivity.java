package br.senac.sp.amicao.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import br.senac.sp.amicao.R;
import br.senac.sp.amicao.api.ApiCategory;
import br.senac.sp.amicao.api.ApiProduct;
import br.senac.sp.amicao.model.Category;
import br.senac.sp.amicao.model.Product;
import br.senac.sp.amicao.util.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductDetailActivity extends AppCompatActivity {
    private ViewGroup mainLayout;
    private Integer id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        final Intent i = getIntent();
        this.id = i.getIntExtra("id", 1);

        mainLayout = findViewById(R.id.productListMainLayout);

        final Retrofit retrofit = Util.getRetrofit();

        ApiProduct api = retrofit.create(ApiProduct.class);
        Call<Product> call = api.get(id);

        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                final Product product = response.body();

                String formatPrice = NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(product.getPrecProduto());

                String url = Util.URL_API + "android/rest/produto/image/" + id;
                ImageView imageView = findViewById(R.id.ivImage);
                ImageLoader imagemLoader = ImageLoader.getInstance();
                imagemLoader.init(ImageLoaderConfiguration.createDefault(getApplicationContext()));
                imagemLoader.displayImage(url, imageView);

                TextView tvName = findViewById(R.id.tvName);
                TextView tvPrice = findViewById(R.id.tvPrice);
                TextView tvEnabled = findViewById(R.id.tvEnabled);
                TextView tvDescription = findViewById(R.id.tvDescription);
                final TextView tvCategory = findViewById(R.id.tvCategory);
                TextView tvStock = findViewById(R.id.tvStock);

                tvName.setText(product.getNomeProduto());
                tvPrice.setText(formatPrice);
                if (product.isAtivoProduto()) {
                    tvEnabled.setTextColor(getResources().getColor(R.color.colorEnabled));
                    tvEnabled.setText("DISPONÍVEL");
                } else {
                    tvEnabled.setTextColor(getResources().getColor(R.color.colorDisabled));
                    tvEnabled.setText("INDISPONÍVEL");
                }
                tvDescription.setText(product.getDescProduto());
                if (product.getIdCategoria() == 0)
                    tvCategory.setText("SEM CATEGORIA");
                else {

                    ApiCategory apiCategory = Util.getRetrofit().create(ApiCategory.class);

                    Call<List<Category>> callCategory = apiCategory.getAll();

                    callCategory.enqueue(new Callback<List<Category>>() {
                        @Override
                        public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                            List<Category> categories = response.body();
                            for (Category category: categories)
                                if (category.getIdCategoria() == product.getIdCategoria())
                                    tvCategory.setText(category.getNomeCategoria());
                        }

                        @Override
                        public void onFailure(Call<List<Category>> call, Throwable t) {
                            Util.showDialog("Erro de Conexão", "Erro", getApplicationContext());
                            t.printStackTrace();
                        }
                    });
                }
                tvStock.setText(String.valueOf(product.getQtdMinEstoque()));
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                Util.showDialog("Erro de Conexão", "Erro", getApplicationContext());
                t.printStackTrace();
            }
        });

    }
}
