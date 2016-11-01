package es.unavarra.tlm.dscr_10.listapartidas;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by nex on 5/11/15.
 */


@JsonIgnoreProperties(ignoreUnknown = true)
public class  Game {

    private String token;
    private Player creator;
    private int available;


    public String getToken() {
        return this.token;
    }

    @JsonProperty("token")
    public void setCreator(String token) {
        this.token = token;
    }

    public Player getCreator() {
        return this.creator;
    }

    @JsonProperty("creator")
    public void setPlayer(Player creator) {
        this.creator = creator;
    }

    public int getAvailable() {
        return this.available;
    }

    @JsonProperty("available")
    public void setAvailable(int available) {
        this.available = available;
    }

}