package br.senac.sp.victor.viacep.interfaces;

import br.senac.sp.victor.viacep.models.ViaCEP;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiViaCEP {
    @GET("/{cep}/json")
    Call<ViaCEP> getCEP(@Path(value= "cep", encoded = true) String cep);
}