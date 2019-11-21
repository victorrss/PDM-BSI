package br.senac.sp.amicao.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
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

public class CategoryListFragment extends Fragment {
    private ViewGroup mainLayout;

    public CategoryListFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category_list, container, false);
        mainLayout = view.findViewById(R.id.categoryListMainLayout);

        ApiCategory api = Util.getRetrofit().create(ApiCategory.class);
        Call<List<Category>> call = api.getAll();

        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                List<Category> list = response.body();
                for (Category category : list)
                    addCard(category.getNomeCategoria(), category.getIdCategoria());
            }
            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Util.showDialog("Erro de Conexão", "Erro", getActivity().getApplicationContext());
                t.printStackTrace();
            }
        });
        return view;
    }

    private void addCard(String title, final Integer id) {
        CardView cardview = (CardView) LayoutInflater.from(getContext()).inflate(R.layout.cardview_category, mainLayout, false);

        TextView txtTitle = cardview.findViewById(R.id.txtNome);
        txtTitle.setText(title);

        cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Util.categoryId = id;
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frag_container, new ProductListFragment())
                        .commit();
            }
        });

        mainLayout.addView(cardview);
    }
}
