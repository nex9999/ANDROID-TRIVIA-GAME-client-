package es.unavarra.tlm.dscr_10.Juego;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import es.unavarra.tlm.dscr_10.listapartidas.Player;

/**
 * Created by root on 26/11/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GameDiceResponse {

    Integer shift;
    Move left, right;

    public Integer getShift(){
        return this.shift;
    }

    @JsonProperty("shift")
    public void setToken(Integer shift){
        this.shift = shift;
    }

    public Move getLeft(){
        return this.left;
    }

    @JsonProperty("left")
    public void setLeft(Move left){
        this.left = left;
    }

    public Move getRight(){
        return this.right;
    }

    @JsonProperty("right")
    public void setRight(Move right){
        this.right = right;
    }


}
