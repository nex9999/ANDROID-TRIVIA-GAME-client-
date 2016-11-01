package es.unavarra.tlm.dscr_10.CarpetaLogin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)//ignorar propiedades no conocidas del fichero
public class Login {

    private String email;
        private String password;

    public String getEmail() {
        return this.email;
    }

    @JsonProperty("email")
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    @JsonProperty("password")//esta propiedad es inamovible ya que es la que relaciona la propiedad del fichero JSON con la clase JAVA
    public void setPassword(String password) {
        this.password = password;
    }
}
