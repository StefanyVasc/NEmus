package com.ufrpe.nemus_app.nemus.controller;

import android.content.Context;
import android.media.MediaPlayer;

import com.ufrpe.nemus_app.nemus.R;
import com.ufrpe.nemus_app.nemus.model.Music;
import com.ufrpe.nemus_app.nemus.model.PlayList;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Ricardo Silva on 05/05/2018.
 */
public class Controller {

    private MediaPlayer player;
    private static Controller instance = new Controller();
    private Context context;
    private PlayList currentPlayList;
    private boolean challengeMode = false;
    private boolean duoMode = false;
    private ArrayList<String> musicNames = new ArrayList<>();
    private boolean animationsEnabled;
    private boolean menuMusicEnabled;
    public PlayList[] playLists;

    /**Singleton Builder**/
    private Controller(){

        //TODO get config parameters in persistence
        animationsEnabled = true;
        menuMusicEnabled = true;

        playLists = new PlayList[7];

        musicNames.add("Que nem jiló");
        musicNames.add("ABC do Sertão");
        musicNames.add("A triste partida");
        musicNames.add("Caboclo Nordestino");
        musicNames.add("Sabiá");
        musicNames.add("Lá do Sertão");
        musicNames.add("A vida do viajante");
        musicNames.add("Xote ecológico");
        musicNames.add("Asa branca");
        musicNames.add("O xote das meninas");
        musicNames.add("Feira de Caruaru");
        musicNames.add("Respeita Januário");
        musicNames.add("A morte do vaqueiro");
        musicNames.add("Pagode Russo");
        musicNames.add("Morena");
        musicNames.add("Assum Preto");
        musicNames.add("Riacho do Navio");
        musicNames.add("Samarica Parteira");
        musicNames.add("Nem se despediu de mim");
        musicNames.add("Sangrando");

        currentPlayList = new PlayList().getDebugPlaylist();

    }

    public void setCurrentPlayList(PlayList currentPlayList) {
        this.currentPlayList = currentPlayList;
    }

    public void setContext(Context context) {
        getInstance().context = context;
    }

    public static Controller getInstance() {
        return instance;
    }

    public void playBackgroundMusic(){
        if (menuMusicEnabled) {
            player = MediaPlayer.create(context, R.raw.qui_nem_jilo_pif);
            player.start();
            player.setLooping(true);
        }
    }

    public void pauseBackgroundMusic(){
        if (player != null){
            try {
                player.stop();
                player.release();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void playMusic(Music music){
        player = MediaPlayer.create(context, music.getRawID());
        player.setLooping(false);
        player.start();
    }

    public void stopMusic(){
        if (player != null){
            try {
                player.stop();
                player.release();
            } catch (IllegalStateException e){
                e.printStackTrace();
            }
        }
    }

    public String[] getRandomNames(){
        String[] names = {"", "", ""};
        while (names[0].equals("") || names[1].equals("") || names[2].equals("")){
            String musicName = currentPlayList.getCurrentMusic().getName();
            ArrayList<String> allNames = (ArrayList<String>) musicNames.clone();
            Random r = new Random();
            int randIndex = r.nextInt(allNames.size());
            if (!allNames.get(randIndex).equals(musicName) && names[0].equals("")){
                names[0] = allNames.get(randIndex);
                allNames.remove(randIndex);
            }
            randIndex = r.nextInt(allNames.size());
            if (!allNames.get(randIndex).equals(musicName) && names[1].equals("")){
                names[1] = allNames.get(randIndex);
                allNames.remove(randIndex);
            }
            randIndex = r.nextInt(allNames.size());
            if (!allNames.get(randIndex).equals(musicName) && names[2].equals("")){
                names[2] = allNames.get(randIndex);
                allNames.remove(randIndex);
            }
        }

        return names;
    }

    public boolean isAnimationsEnabled() {
        return animationsEnabled;
    }

    public boolean isMenuMusicEnabled() {
        return menuMusicEnabled;
    }

    public void setAnimationsEnabled(boolean animationsEnabled) {
        this.animationsEnabled = animationsEnabled;
    }

    public void setMenuMusicEnabled(boolean menuMusicEnabled) {
        this.menuMusicEnabled = menuMusicEnabled;
    }

    public void initializePlaylists(){
        PlayList playList = new PlayList();
        playList.setName("Viver");
        playList.addMusic(new Music("Que nem jiló", R.raw.qui_nem_jilo, 0));
        playList.addMusic(new Music("O xote das meninas", R.raw.o_xote_das_meninas, 1));
        playList.addMusic(new Music("Riacho do navio", R.raw.riacho_do_navio, 2));
        playList.addMusic(new Music("São João na roça", R.raw.sao_joao_na_roca, 3));
        playList.addMusic(new Music("Assum Preto", R.raw.assum_preto, 4));
        playList.setCurrentMusic(playList.getRandomMusic(false));
        playList.setCurrentMusicPosition(0);
        playLists[1] = playList;

        playList = new PlayList();
        playList.setName("Trabalhar");
        playList.addMusic(new Music("O cheiro da Carolina", R.raw.o_cheiro_da_carolina, 0));
        playList.addMusic(new Music("Pagode russo", R.raw.pagode_russo, 1));
        playList.addMusic(new Music("Nem se despediu de mim", R.raw.nem_se_despediu_de_mim, 2));
        playList.addMusic(new Music("Baião", R.raw.baiao, 3));
        playList.addMusic(new Music("A morte do vaqueiro", R.raw.a_morte_do_vaqueiro, 4));
        playList.setCurrentMusic(playList.getRandomMusic(false));
        playList.setCurrentMusicPosition(0);
        playLists[2] = playList;

        PlayList playList1 = new PlayList();
        playList1.setName("Desafio");
        playList1.addMusic(new Music("Que nem jiló", R.raw.qui_nem_jilo, 0));
        playList1.addMusic(new Music("A morte do vaqueiro", R.raw.a_morte_do_vaqueiro, 4));
        playList1.addMusic(new Music("Baião", R.raw.baiao, 3));
        playList1.addMusic(new Music("Nem se despediu de mim", R.raw.nem_se_despediu_de_mim, 2));
        playList1.addMusic(new Music("Pagode russo", R.raw.pagode_russo, 1));
        playList1.addMusic(new Music("O cheiro da Carolina", R.raw.o_cheiro_da_carolina, 0));
        playList1.addMusic(new Music("O xote das meninas", R.raw.o_xote_das_meninas, 1));
        playList1.addMusic(new Music("Riacho do navio", R.raw.riacho_do_navio, 2));
        playList1.addMusic(new Music("São João na roça", R.raw.sao_joao_na_roca, 3));
        playList1.addMusic(new Music("Assum Preto", R.raw.assum_preto, 4));
        playList1.setCurrentMusic(playList.getRandomMusic(false));
        playList1.setCurrentMusicPosition(0);
        playLists[0] = playList1;

    }

    public void resetPlayList(){
        currentPlayList = playLists[1].copy();
    }

    public void setChallengeMode(boolean challengeMode) {
        this.challengeMode = challengeMode;
    }

    public void setDuoMode(boolean duoMode) {
        this.duoMode = duoMode;
    }

    public boolean isChallengeMode() {
        return challengeMode;
    }

    public boolean isDuoMode() {
        return duoMode;
    }

    public PlayList getCurrentPlayList() {
        return currentPlayList;
    }
}
