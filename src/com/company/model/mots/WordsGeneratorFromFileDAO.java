package com.company.model.mots;

import com.company.model.AppConfig.FilesPaths;
import com.company.model.mots.*;
import com.company.model.mots.cases.IndicationFactory;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class WordsGeneratorFromFileDAO extends WordsGeneratorDAO {

    private String wordsFilePath ;
    private ArrayList<Mot> mots = new ArrayList<>();
    private BufferedReader bufferedReader = null;

    public WordsGeneratorFromFileDAO(String wordsFilePath) {
        this.wordsFilePath = wordsFilePath;
    }

    @Override
    protected ArrayList<Mot> createMotsObjects() {
        return this.mots;
    }

    @Override
    protected void readDataSouce() throws DAException {
        IndicationFactory indicationFactory = new IndicationFactory();
        String ligne = null;
        try {
            ligne = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (ligne != null){ //On lit le fichier ligne par ligne
            System.out.println(ligne);
            StringTokenizer tokenizer = new StringTokenizer(ligne,";"); //On découpe la ligne selon le point virgule
            String type = tokenizer.nextToken(); //on récupère le Type qui le premier champs
            String question = tokenizer.nextToken();// on récupère la valeur de l'indication
            String reponse = tokenizer.nextToken(); // on récupère la valeur du mot
            Indication ind = indicationFactory.createIndication(type,question);


            mots.add(new Mot(ind,reponse));
            try {
                ligne = bufferedReader.readLine();
            } catch (IOException e) {
                throw new DAException("Erreur dans la lecture du fichier de mots",e);
            }
        }
    }

    @Override
    protected void connectToDataSouce() throws DAException {
        try {
            bufferedReader = new BufferedReader(new FileReader(wordsFilePath));
        } catch (FileNotFoundException e) {
            throw new DAException("Impossible de trouver le fichier",e);
        }
    }
}
