package es.unavarra.tlm.dscr_10.Juego;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by root on 26/11/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GameResponseResponse {

    boolean correct, hq;
    String category;

    public boolean getCorrect(){
        return this.correct;
    }
    @JsonProperty("correct")
    public void setCorrect(boolean correct){
        this.correct = correct;
    }

    public boolean getHq(){
        return this.hq;
    }
    @JsonProperty("hq")
    public void setHq(boolean hq){
        this.hq = hq;
    }

    public String getCategory(){
        return this.category;
    }
    @JsonProperty("category")
    public void setCategory(String category){
        this.category = category;
    }
}
