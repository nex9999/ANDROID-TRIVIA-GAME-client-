package es.unavarra.tlm.dscr_10.CarpetaLogin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;




@JsonIgnoreProperties(ignoreUnknown = true)
public class  LoginResponseMessage {

    private String token;
    private String name;

    public String getToken() {
        return this.token;
    }

    @JsonProperty("token")
    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return this.name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

}