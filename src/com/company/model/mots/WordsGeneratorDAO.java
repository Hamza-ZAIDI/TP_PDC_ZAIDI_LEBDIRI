package com.company.model.mots;

import java.util.ArrayList;

public abstract class WordsGeneratorDAO  {
    ArrayList<Mot> findWords() throws DAException{
        connectToDataSouce();
        readDataSouce();
        return createMotsObjects();
    }

    protected abstract ArrayList<Mot> createMotsObjects();

    protected abstract void readDataSouce() throws DAException;

    protected abstract void connectToDataSouce() throws DAException;

}
