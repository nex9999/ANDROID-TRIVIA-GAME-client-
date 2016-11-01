package es.unavarra.tlm.dscr_10.Juego;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by root on 26/11/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseRequestMessage {
    private String token;
    private Integer response;


    public String getToken() {
        return this.token;
    }

    @JsonProperty("token")
    public void setToken(String token) {
        this.token = token;
    }

    public Integer getResponse(){
        return this.response;
    }
    @JsonProperty("response")
    public void setResponse(Integer response){
        this.response = response;
    }

}
