package es.unavarra.tlm.dscr_10.Juego;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import es.unavarra.tlm.dscr_10.listapartidas.Game;
import es.unavarra.tlm.dscr_10.listapartidas.Player;

/**
 * Created by root on 24/11/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GameInfoResponse {

    private String token;
    private Player creator;
    private List<Player> players;
    private Integer round, turn;
    private List<Cell> board;
    private List<Status> status;

    public String getToken(){
        return this.token;
    }

    @JsonProperty("token")
    public void setToken(String token){
        this.token = token;
    }

    public Player getCreator(){
        return this.creator;
    }

    @JsonProperty("creator")
    public void setCreator(Player creator){
        this.creator = creator;
    }

    public List<Player> getPlayers(){
        return this.players;
    }

    @JsonProperty("players")
    public void setPlayers(List<Player> players){
        this.players = players;
    }

    public Integer getRound(){
        return round;
    }

    @JsonProperty("round")
    public void setRound(Integer round){
        this.round = round;
    }

    public Integer getTurn(){
        return turn;
    }

    @JsonProperty("turn")
    public void setTurn(Integer turn){
        this.turn = turn;
    }

    public List<Cell> getBoard(){
        return board;
    }

    @JsonProperty("board")
    public void setBoard(List<Cell> board){
        this.board = board;
    }

    public List<Status> getStatus(){
        return status;
    }

    @JsonProperty("status")
    public void setStatus(List<Status> status){
        this.status = status;
    }

}
