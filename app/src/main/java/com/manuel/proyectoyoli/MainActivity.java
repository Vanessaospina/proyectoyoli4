package com.manuel.proyectoyoli;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.manuel.proyectoyoli.Api.PokeApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    public TextView pokemon;
    ListaPokemonAdapter listaPokemonAdapter;
    RecyclerView recyclerView;
     Retrofit retrofit;
     private final String TAG="POKEAPI";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.vanessa_emulo);
        listaPokemonAdapter= new ListaPokemonAdapter(this);
        recyclerView.setAdapter(listaPokemonAdapter);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this, RecyclerView.VERTICAL,true);
        recyclerView.setLayoutManager(linearLayoutManager);


         retrofit = new Retrofit.Builder()
                 .baseUrl("https://pokeapi.co/api/v2/")
                 .addConverterFactory(GsonConverterFactory.create())
                 .build();
         obtenerDatos();
    }

    private void obtenerDatos() {
        PokeApiService service= retrofit.create(PokeApiService.class);
        Call<PokemonRespuesta> pokemonRespuestaCall= service.obtenerListaPokemon();
        pokemonRespuestaCall.enqueue(new Callback<PokemonRespuesta>() {
            @Override
            public void onResponse(Call<PokemonRespuesta> call, Response<PokemonRespuesta> response) {
              if (response.isSuccessful()) {
                  PokemonRespuesta pokemonRespuesta = response.body();
                  List<Pokemon> listaPokemon = pokemonRespuesta.getResult();
                  for ( int i = 0; i <listaPokemon.size(); i++){
                      Pokemon p= listaPokemon.get(i);
                      Log.e(TAG,"pokemon:"+ p.getUrl());

                  }

                  listaPokemonAdapter.add((ArrayList<Pokemon>) listaPokemon);


              }
              else{
                  Log.e(TAG,"onResponse"+ response.errorBody());
              }
            }

            @Override
            public void onFailure(Call<PokemonRespuesta> call, Throwable t) {
                Log.e(TAG, "onliFailure: "+ t.getMessage());

            }
        });
    }
}