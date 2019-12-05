package br.senac.sp.amicao.api;

import java.util.List;

import br.senac.sp.amicao.model.Category;
import br.senac.sp.amicao.model.Customer;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiCustomer {
    @POST("/android/rest/cliente")
    Call<Customer> login(@Body Customer customer);

    @POST("/android/rest/cliente/cadastro")
    Call<Customer> register(@Body Customer customer);
}