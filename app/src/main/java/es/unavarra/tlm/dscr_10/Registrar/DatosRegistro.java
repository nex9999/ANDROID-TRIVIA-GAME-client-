package es.unavarra.tlm.dscr_10.Registrar;

import com.fasterxml.jackson.annotation.JsonProperty;

import es.unavarra.tlm.dscr_10.CarpetaLogin.Login;

/**
 * Created by nex on 5/11/15.
 */
public class DatosRegistro extends Login {

    private String name;


    public String getName() {
        return this.name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }


}
