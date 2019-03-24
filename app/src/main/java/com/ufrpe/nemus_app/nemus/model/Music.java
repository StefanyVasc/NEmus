package com.ufrpe.nemus_app.nemus.model;

import com.ufrpe.nemus_app.nemus.R;

/**
 * Created by Ricardo Silva on 06/05/2018.
 */
public class Music {

    private String name;
    private int rawID;
    private int id;

    public Music(String name, int rawID, int id){
        this.name = name;
        this.rawID = rawID;
        this.id = id;
    }

    public Music(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRawID() {
        return rawID;
    }

    public void setRawID(int rawID) {
        this.rawID = rawID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
