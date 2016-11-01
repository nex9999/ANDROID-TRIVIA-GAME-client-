package es.unavarra.tlm.dscr_10.Juego;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by root on 26/11/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MoveRequestMessage {
    private String token;
    private String to;


    public String getToken() {
        return this.token;
    }

    @JsonProperty("token")
    public void setToken(String token) {
        this.token = token;
    }

    public String getTo(){
        return this.to;
    }
    @JsonProperty("to")
    public void setTo(String to){
        this.to = to;
    }

}
