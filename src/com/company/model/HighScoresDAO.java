package com.company.model;

import java.util.TreeMap;

public interface HighScoresDAO {

    TreeMap<Integer,String> getHighScores();
    void storeHighScores();

}
