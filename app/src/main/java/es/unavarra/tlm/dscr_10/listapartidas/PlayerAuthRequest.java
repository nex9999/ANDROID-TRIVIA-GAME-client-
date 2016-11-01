package es.unavarra.tlm.dscr_10.listapartidas;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by nex on 12/11/15.
 */




@JsonIgnoreProperties(ignoreUnknown = true)
public class PlayerAuthRequest {

    private String token;


    public String getToken() {
        return this.token;
    }

    @JsonProperty("token")
    public void setToken(String token) {
        this.token = token;
    }


}