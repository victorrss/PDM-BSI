package br.senac.sp.amicao.api;

import java.util.List;
import br.senac.sp.amicao.model.Product;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiProduct {
    @GET("/android/rest/produto")
    Call<List<Product>> getAll();

    @GET("/android/rest/produto/{id}")
    Call<Product> get(@Path("id") Integer id);

    @GET("/android/rest/produto/{name}")
    Call<List<Product>> getByName(@Path("name") String name);

    @GET("/android/rest/produto/categoria/{id}")
    Call<List<Product>> getByCategory(@Path("id") Integer id);

    @GET("/android/rest/produto/{name}/{categoryId}")
    Call<List<Product>> getByNameAndCategoryId(@Path("name") String name, @Path("categoryId") Integer categoryId);
}