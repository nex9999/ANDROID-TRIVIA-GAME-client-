package es.unavarra.tlm.dscr_10.Juego;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by root on 24/11/15.
 */
public class Status {

    private String action;
    private Integer position;
    private List<String> hq;

    public String getAction(){
        return this.action;
    }

    @JsonProperty("action")
    public void setAction(String action){
        this.action = action;
    }

    public Integer getPosition(){
        return this.position;
    }

    @JsonProperty("position")
    public void setPosition(Integer position){
        this.position = position;
    }

    public List<String> getHq(){
        return this.hq;
    }

    @JsonProperty("hq")
    public void setHq(List<String> hq){
        this.hq = hq;
    }
}
