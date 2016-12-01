package be.interface3.ssingh.werewolf.model;

import java.io.Serializable;

/**
 * Created by s.singh on 29/09/2016.
 */
public class User implements Serializable {

    private int userId;
    private String username;
    private String role;
    private boolean connectedStatus;
    private boolean activityStatus;
    private boolean livingStatus;
    private boolean isVictim;
    private boolean isGM;

    public User() {}
    public User(int userId, String username, boolean activityStatus, boolean livingStatus, String role) {
        this.userId = userId;
        this.username = username;
        this.activityStatus = activityStatus;
        this.livingStatus = livingStatus;
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isConnectedStatus() {
        return connectedStatus;
    }

    public void setConnectedStatus(boolean connectedStatus) {
        this.connectedStatus = connectedStatus;
    }

    public boolean isActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(boolean activityStatus) {
        this.activityStatus = activityStatus;
    }

    public boolean isLivingStatus() {
        return livingStatus;
    }

    public void setLivingStatus(boolean livingStatus) {
        this.livingStatus = livingStatus;
    }

    public boolean isVictim() {
        return isVictim;
    }

    public void setVictim(boolean victim) {
        isVictim = victim;
    }

    public boolean isGM() {
        return isGM;
    }

    public void setGM(boolean GM) {
        isGM = GM;
    }

    @Override
    public String toString() {
        return (getUsername());
    }
}
