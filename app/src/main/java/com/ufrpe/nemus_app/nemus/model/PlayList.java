package com.ufrpe.nemus_app.nemus.model;

import android.util.Log;

import com.ufrpe.nemus_app.nemus.R;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Ricardo Silva on 06/05/2018.
 */
public class PlayList {

    private String name;
    private ArrayList<Music> musicSet = new ArrayList<>();
    private Music currentMusic;
    private int currentMusicPosition = -1;
    private boolean empty;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Music> getMusicSet() {
        return musicSet;
    }

    public void addMusic(Music music){
        this.musicSet.add(music);
    }

    private void removeCurrentMusic(){
        musicSet.remove(currentMusicPosition);
    }

    public Music getRandomMusic(boolean excluding){
        Random r = new Random();
        Music music = null;
        if (currentMusic == null || !excluding) {
            int randInt = r.nextInt(musicSet.size());
            while (randInt == currentMusicPosition){
                randInt = r.nextInt(musicSet.size());
            }
            music = musicSet.get(randInt);
            currentMusic = music;
            currentMusicPosition = randInt;
        } else {
            removeCurrentMusic();
            if (musicSet.size() > 0) {
                int randInt = r.nextInt(musicSet.size());
                music = musicSet.get(randInt);
                currentMusic = music;
                currentMusicPosition = randInt;
            } else {
                empty = true;
            }
        }
        return music;
    }

    public PlayList getDebugPlaylist(){
        PlayList playList = new PlayList();
        playList.setName("Debug Playlist");
        playList.addMusic(new Music("Que nem jiló", R.raw.qui_nem_jilo_pif, 0));
        playList.addMusic(new Music("Numa sala de reboco", R.raw.numa_sala_de_reboco_pif, 1));
        playList.addMusic(new Music("Eu só quero um xodó", R.raw.eu_so_quero_um_xodo_pif, 2));
        playList.addMusic(new Music("Asa branca", R.raw.asa_branca_pif, 3));
        playList.setCurrentMusic(playList.getRandomMusic(false));
        playList.setCurrentMusicPosition(0);

        Log.d("Debug Music", playList.getCurrentMusic().getName());

        return playList;
    }

    private void setMusicSet(ArrayList<Music> musicSet){
        this.musicSet = musicSet;
    }

    public Music getCurrentMusic() {
        return currentMusic;
    }

    public void setCurrentMusic(Music currentMusic) {
        this.currentMusic = currentMusic;
    }

    public int getCurrentMusicPosition() {
        return currentMusicPosition;
    }

    public void setCurrentMusicPosition(int currentMusicPosition) {
        this.currentMusicPosition = currentMusicPosition;
    }

    public PlayList copy(){
        PlayList pl = new PlayList();
        pl.setCurrentMusicPosition(this.getCurrentMusicPosition());
        pl.setCurrentMusic(this.getCurrentMusic());
        pl.setName(this.getName());
        pl.setMusicSet(this.getMusicSet());
        return pl;
    }

    public boolean isEmpty() {
        return empty;
    }
}
