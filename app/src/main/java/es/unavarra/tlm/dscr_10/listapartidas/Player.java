package es.unavarra.tlm.dscr_10.listapartidas;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by nex on 5/11/15.
 */


@JsonIgnoreProperties(ignoreUnknown = true)
public class  Player {

    private String name;


    public String getName() {
        return this.name;
    }

    @JsonProperty("name")
    public void setGames(String name) {
        this.name = name;
    }


}