package es.unavarra.tlm.dscr_10.listapartidas;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public class  ListaPartidasResponseMessage {

    private List<Game> games;


    public List<Game> getGames() {
        return this.games;
    }

    @JsonProperty("games")
    public void setGames(List<Game> games) {
        this.games = games;
    }

    public Game getGame(int position) {
        return this.games.get(position);
    }

    public Game setGame(int position,Game game) {
        return this.games.set(position, game);
    }

    public int getSize() {
        return this.games.size();
    }



}
