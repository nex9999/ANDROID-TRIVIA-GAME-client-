package es.unavarra.tlm.dscr_10.Juego;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by root on 26/11/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)

public class GameMoveResponse {
    String question;
    String[] responses;
    public String getQuestion(){
        return this.question;
    }

    @JsonProperty("question")
    public void setQuestion(String question){
        this.question = question;
    }

    public String[] getResponses(){
        return this.responses;
    }

    @JsonProperty("responses")
    public void setResponses(String[] responses){
        this.responses = responses;
    }


}
