package be.interface3.ssingh.werewolf.model;

import java.io.Serializable;

/**
 * Created by s.singh on 30/09/2016.
 */
public class Party implements Serializable {
    private String name;
    private String password;
    private boolean openToPublic;
    private String roundGM;
    private int playerCount;
    private int gameState;

    public Party() {
    }

    public Party(String roundGM, String name, String password, int playerCount) {
        this.name = name;
        this.password = password;
        this.roundGM = roundGM;
        this.playerCount = playerCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isOpenToPublic() {
        return openToPublic;
    }

    public void setOpenToPublic(boolean openToPublic) {
        this.openToPublic = openToPublic;
    }

    public String getRoundGM() {
        return roundGM;
    }

    public void setRoundGM(String roundGM) {
        this.roundGM = roundGM;
    }

    public int getPlayerCount() {
        return playerCount;
    }

    public void setPlayerCount(int playersNumber) {
        this.playerCount = playersNumber;
    }

    public int getGameState() {
        return gameState;
    }

    public void setGameState(int gameState) {
        this.gameState = gameState;
    }
}
