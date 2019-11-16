package br.senac.sp.amicao.ui;

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
import br.senac.sp.amicao.api.ApiProduct;
import br.senac.sp.amicao.model.Product;
import br.senac.sp.amicao.util.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductListFragment extends Fragment {
    private ViewGroup mainLayout;

    public ProductListFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_product_list, container, false);
        mainLayout = view.findViewById(R.id.mainLayout);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Util.URL_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiProduct api = retrofit.create(ApiProduct.class);
        Call<List<Product>> call = api.getAll();

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
                Util.showDialog("Erro de Conexão", "Erro", getContext());
                t.printStackTrace();
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    private void addCard(String title, String msg, int id) {
        /*
            System.out.println("Titulo: " + title);
            System.out.println("Mensagem: " + msg);
            System.out.println("Id: " + id);
        */

        final CardView cardview = (CardView) LayoutInflater.from(getActivity()).inflate(R.layout.cardview_product, mainLayout, false);

        TextView txtTitle = cardview.findViewById(R.id.txtNome);
        TextView txtMsg = cardview.findViewById(R.id.txtPreco);

        txtTitle.setText(title);
        txtMsg.setText(msg);

        String url = Util.URL_API + "android/rest/produto/image/" + id;
        ImageView imageView = cardview.findViewById(R.id.image);
        ImageLoader imagemLoader = ImageLoader.getInstance();
        imagemLoader.init(ImageLoaderConfiguration.createDefault(getContext()));
        imagemLoader.displayImage(url, imageView);

        mainLayout.addView(cardview);
    }
}
