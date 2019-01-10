package com.company.model;

import java.util.ArrayList;
import java.util.TreeMap;

public class HighScoresHandler {



    private HighScoresDAO highScoresDAO;
    private TreeMap<Integer,String> highScores;
    public HighScoresHandler(HighScoresDAO highScoresDAO) {
        this.highScoresDAO = highScoresDAO;
    }

    public void addHighScore(Session session){
        ArrayList<Integer> scores = session.getScores();
        String player = session.getPlayer().getPseudonyme();
        highScores = highScoresDAO.getHighScores();
        if (highScores == null) highScores = new TreeMap<>();
        for (int i :scores){
            highScores.put(i,player);
        }
        storeHighScores();
    }

    public TreeMap<Integer,String> getHighScores(){
        return highScores;
    }

    public void storeHighScores(){
        highScoresDAO.storeHighScores();
    }

}
