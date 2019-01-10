package com.company.model.mots.cases;

import com.company.model.HighScoresDAO;

import java.io.*;
import java.util.TreeMap;

public class HighScoresFileDAO implements HighScoresDAO {
    private String highScoresFilePath;
    private TreeMap<Integer,String> highScores;
    public HighScoresFileDAO(String highScoresFilePath) {
        this.highScoresFilePath = highScoresFilePath;
    }



    @Override
    public TreeMap<Integer, String> getHighScores() {
        if(highScores == null){
            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(new File(highScoresFilePath)));
                highScores = (TreeMap<Integer, String>) objectInputStream.readObject();
                objectInputStream.close();
            } catch (IOException |ClassNotFoundException e) {
                highScores = null;
            }
        }
        return highScores;
    }

    @Override
    public void storeHighScores() {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File(highScoresFilePath)));
            objectOutputStream.writeObject(highScores);
            objectOutputStream.close();
        } catch (IOException e) {

        }
    }
}
