package es.unavarra.tlm.dscr_10.Juego;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by root on 24/11/15.
 */
public class Cell {

    Integer position;
    String type, category;

    public Integer getPosition(){
        return this.position;
    }

    @JsonProperty("position")
    public void setPosition(Integer position){
        this.position = position;
    }

    public String getType(){
        return this.type;
    }

    @JsonProperty("type")
    public void setType(String type){
        this.type = type;
    }

    public String getCategory(){
        return this.category;
    }

    @JsonProperty("category")
    public void setCategory(String category){
        this.category = category;
    }

}
