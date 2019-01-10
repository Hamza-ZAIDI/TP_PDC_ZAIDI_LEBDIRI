package com.company.model.mots;

import java.io.*;
import java.util.*;

import static java.lang.Math.abs;

/**
 * Created by Amine on 17/04/2017.
 */
public class WordsGenerator {


    private WordsGeneratorDAO wordsGeneratorDAO;
    private ArrayList<Mot> mots = new ArrayList<>();
    private HashSet<Mot> motsSeance =  new HashSet<>();
    private final int NB_MOTS_SEANCE = 10;

    public WordsGenerator(WordsGeneratorDAO wordsGeneratorDAO) {
        this.wordsGeneratorDAO = wordsGeneratorDAO;
    }

    /*
    * Génrer une liste de dix mots choisis aléatoirement à partire du fichier
     */
    public HashSet<Mot> genererListeMotsSeance() throws DAException {

        mots = wordsGeneratorDAO.findWords();
        System.out.println(mots);
        motsSeance = new HashSet<>();
        Random random = new Random();
        while (motsSeance.size()<NB_MOTS_SEANCE){ // Tant que nombre des mots est inferieur à NB_MOT_SEANCE
            int a = abs(random.nextInt());
            int b = mots.size();  // On ajoute un mot aléatoire de la liste des mot tiré du fichier
            int i = a%b;
            motsSeance.add(mots.get(i));
        }

        return motsSeance;
    }


    public HashSet<Mot> getMotsSeance() {
        return motsSeance;
    }
}
