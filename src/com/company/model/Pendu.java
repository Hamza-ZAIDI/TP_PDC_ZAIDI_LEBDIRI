package com.company.model;

import com.company.model.mots.DAException;
import com.company.model.mots.Mot;

import java.io.*;
import java.util.*;

/**
 * Created by Amine on 17/04/2017.
 */
public class Pendu extends Observable{
    private Session sessionActuel;
    private PlayersHandler playersHandler;
    private boolean sessionTerminee;
    private ArrayList<Observer> observers = new ArrayList<>();
    private HighScoresHandler highScoresHandler;
    private static Pendu instance;

    private Pendu(){}

    public static Pendu getPendu(){
        if(instance == null){
            instance = new Pendu();
        }
        return instance;
    }

    public void setPlayersHandler(PlayersHandler playersHandler) {
        this.playersHandler = playersHandler;
    }

    public void setHighScoresHandler(HighScoresHandler highScoresHandler) {
        this.highScoresHandler = highScoresHandler;
    }



    public Player getPlayer(String pseudonyme) throws DAException {
        return playersHandler.findPlayer(pseudonyme);
    }
    public Session getSessionActuel() {return sessionActuel;}
    public boolean isSessionTerminee() {
        return sessionTerminee;
    }




    /**
     * Récupère les meilleurs scores depuis le fichier des meilleurs scores
     * @return TreeMap<Integer, String>
     */
    public TreeMap<Integer, String> getHighScores() {

        return highScoresHandler.getHighScores();
    }




    /**
     * Vérifie si un pseudonyme existe ou pas
     * @param pseudonyme
     * @return boolean
     * @throws LoginNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public boolean LoginCheck(String pseudonyme) throws LoginNotFoundException, IOException, ClassNotFoundException, DAException {
        if (playersHandler.findPlayer(pseudonyme) == null) throw new LoginNotFoundException("Ce pseudonyme n'existent pas");
        return true;
    }

    /**
     * Ajoute un nouveau joueur au jeu
     * @param player
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void AddPlayer(Player player) throws IOException, ClassNotFoundException, IllegalNicknameException, DAException {
        playersHandler.addPlayer(player);
    }
    /**
     * Vérifie si le caractère écrit par l'utilisateur est correcte ou pas
     * @param c
     * @param indexCase
     */
    public void VerificationCase(char c,int indexCase){
        if (!sessionActuel.isSessionTerminee()){
            sessionActuel.VerificationCase(c,indexCase);
            if (sessionActuel.isSessionTerminee()) EndSession();
        }

    }

    /**
     * Commence une nouvelle session du jeu pour un joueur donné
     * @param player
     */
    public void StartSession(Player player){
        try {
            sessionActuel = new Session(player);
        } catch (DAException e) {
            e.printStackTrace();
        }
    }

    /**
     * Termine la session en cours et sauvegarde les scores
     */
    private void EndSession(){
        sessionTerminee = true;
        addHighScores();
        notifyObservers();
        try {
            AddPlayer(sessionActuel.getPlayer());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalNicknameException e) {
            e.printStackTrace();
        } catch (DAException e) {
            e.printStackTrace();
        }

    }


    /**
     * Mis à jour les meilleurs scores et les sauvegarde
     */
    private void addHighScores(){
        highScoresHandler.addHighScore(sessionActuel);
    }

    /** Des méthodes de notification (principalement utilisées avec l'interface)*/


    @Override
    public synchronized void addObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public synchronized void deleteObserver(Observer o) {
        super.deleteObserver(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers){
            o.update((Observable) this,sessionTerminee);
        }
    }
}

