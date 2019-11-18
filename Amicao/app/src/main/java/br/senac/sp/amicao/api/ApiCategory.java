package br.senac.sp.amicao.api;

import java.util.List;

import br.senac.sp.amicao.model.Category;
import br.senac.sp.amicao.model.Product;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiCategory {
    @GET("/android/rest/categoria")
    Call<List<Category>> getAll();
}