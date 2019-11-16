package br.senac.sp.amicao.api;

import java.util.List;
import br.senac.sp.amicao.model.Product;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiProduct {
    @GET("/android/rest/produto")
    Call<List<Product>> getAll();
}