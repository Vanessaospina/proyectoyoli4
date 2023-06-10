package com.manuel.proyectoyoli.Api;

import com.manuel.proyectoyoli.PokemonRespuesta;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PokeApiService {
    @GET("pokemon")
    Call <PokemonRespuesta> obtenerListaPokemon();
}
