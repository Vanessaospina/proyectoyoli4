package com.manuel.proyectoyoli;

import java.util.ArrayList;
import java.util.List;

public class PokemonRespuesta {
    private List <Pokemon> results =new ArrayList<Pokemon>();

    public List<Pokemon>getResult(){
        return results;
    }

    public void setResults(List<Pokemon> results) {
        this.results = results;
    }
}
