package br.senac.sp.amicao.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
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

public class ProductListFragment extends Fragment {
    private static String searchTerm;
    private static Integer categoryId;

    private ViewGroup mainLayout;
    private ConstraintLayout layoutFilter;
    private TextView tvFilterCategory;
    private Button btnFilterClear;

    public ProductListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_product_list, container, false);

        mainLayout = view.findViewById(R.id.productListMainLayout);
        layoutFilter = view.findViewById(R.id.layoutFilter);
        tvFilterCategory = view.findViewById(R.id.tvFilterCategory);
        btnFilterClear = view.findViewById(R.id.btnFilterClear);
        SearchView searchView =view.findViewById(R.id.search_view);
        callApis();

        if (searchTerm != null){
            CharSequence cs = searchTerm;
            searchView.setQuery(cs, false);
        }

        // Inflate the layout for this fragment
        return view;
    }

    private void addCard(String title, String msg, final int id) {
        try {
            CardView cardview = (CardView) LayoutInflater.from(getContext()).inflate(R.layout.cardview_product, mainLayout, false);

            TextView txtTitle = cardview.findViewById(R.id.txtNome);
            TextView txtMsg = cardview.findViewById(R.id.txtPreco);

            txtTitle.setText(title == null ? "" : title);
            txtMsg.setText(msg == null ? "" : msg);

            String url = Util.URL_API + "android/rest/produto/image/" + id;
            ImageView imageView = cardview.findViewById(R.id.ivImage);
            ImageLoader imagemLoader = ImageLoader.getInstance();
            imagemLoader.init(Util.getImageLoaderConfig(getActivity().getApplicationContext()));
            imagemLoader.displayImage(url, imageView);

            cardview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getActivity().getApplicationContext(), ProductDetailActivity.class);
                    i.putExtra("id", id);
                    startActivityForResult(i, 1);
                }
            });

            mainLayout.addView(cardview);
        } catch (Exception e){
            //e.printStackTrace();
        }

    }

    public void callApis() {
        this.searchTerm = Util.searchTerm;
        this.categoryId = Util.categoryId;
        mainLayout.removeAllViews();


        ApiProduct api = Util.getRetrofit().create(ApiProduct.class);

        Call<List<Product>> call = null;

        if (searchTerm == null && categoryId == null) { // TODOS OS PRODUTOS
            call = api.getAll();
        } else if (searchTerm == null && categoryId != null) { // SOMENTE POR CATEGORIA
            call = api.getByCategory(this.categoryId);
        } else if (searchTerm != null && categoryId == null) { // SOMENTE PELO NOME
            call = api.getByName(this.searchTerm);
        } else if (searchTerm != null && categoryId != null) { // PELO NOME E CATEGORIA
            call = api.getByNameAndCategoryId(this.searchTerm, this.categoryId);
        }

        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                List<Product> list = response.body();
                for (Product product : list) {
                    String formatValue = NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(product.getPrecProduto());
                    addCard(product.getNomeProduto(), formatValue, product.getIdProduto());
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Util.showDialog("Erro de Conexão", "Erro", getActivity().getApplicationContext());
                t.printStackTrace();
            }
        });


        // view FILTER


        if (categoryId == null)
            layoutFilter.setVisibility(View.GONE);
        else {
            ApiCategory apiCategory = Util.getRetrofit().create(ApiCategory.class);

            Call<List<Category>> callCategory = apiCategory.getAll();

            callCategory.enqueue(new Callback<List<Category>>() {
                @Override
                public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                    List<Category> categories = response.body();
                    for (Category category : categories)
                        if (category.getIdCategoria() == categoryId)
                            tvFilterCategory.setText(category.getNomeCategoria());
                }

                @Override
                public void onFailure(Call<List<Category>> call, Throwable t) {
                    Util.showDialog("Erro de Conexão", "Erro", getActivity().getApplicationContext());
                    t.printStackTrace();
                }
            });
        }

        btnFilterClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Util.categoryId = null;
                categoryId = null;
                layoutFilter.setVisibility(View.GONE);
                callApis();
            }
        });

    }
}
