package br.senac.sp.victor.calculadoraretrofit.interfaces;

import br.senac.sp.victor.calculadoraretrofit.models.Calculadora;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiCalculadora {
    @GET("/examples/getObject.php")
    Call<Calculadora> getObject(@Query("num1") Double num1,
                                @Query("num2") Double num2);
}
