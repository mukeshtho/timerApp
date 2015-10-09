package com.mthotengera.timerapp.model;

/**
 * Created by mt250219 on 10/7/15.
 */
public class WorkoutInfo {
    private int time;
    private String name;
    private String type;

    public WorkoutInfo(int time, String name, String type) {
        this.time = time;
        this.name = name;
        this.type = type;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
