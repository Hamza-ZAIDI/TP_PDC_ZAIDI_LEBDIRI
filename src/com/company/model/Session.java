package com.company.model;

import com.company.model.AppConfig.ParamettreJeux;
import com.company.model.mots.DAException;
import com.company.model.mots.Mot;
import com.company.model.mots.WordsGenerator;

import java.util.*;

/**
 * Created by Amine on 17/04/2017.
 */
public class Session extends Observable {
    private Player player;
    private HashSet<Mot> mots;
    private Mot motActuel;
    private Iterator<Mot> iterator;
    private boolean sessionTerminee;
    private final int nombreEchecsMax = 6;
    private int nombreEchecsActuel;
    private ArrayList<Observer> observers = new ArrayList<>();

    public int getNombreEchecsActuel() {
        return nombreEchecsActuel;
    }

    public Player getPlayer() {
        return player;
    }

    public Session(Player player) throws DAException {
        this.player = player;
        WordsGenerator wordsGenerator = new WordsGenerator(ParamettreJeux.getInstance().getWordsGenertorDAO());
        this.mots = wordsGenerator.genererListeMotsSeance();
        genererCases();
        this.iterator = this.mots.iterator();
        this.motActuel = iterator.next();
        this.sessionTerminee = false;
        this.nombreEchecsActuel = 0 ;

    }

    private void genererCases(){
        for (Mot mot: mots
             ) {
            mot.genererCases();
        }
    }

    public ArrayList<Integer> getScores(){
        return player.getScores();
    }
    public boolean isSessionTerminee() {
        return sessionTerminee;
    }

    public Mot getMotActuel() {
        return motActuel;
    }

    /**
     * Vérifie si le caractère ecrit par le joueur est correcte ou pas
     * @param c
     * @param indexCase
     */
    public void VerificationCase(char c, int indexCase){
        if (motActuel.Verification(c,indexCase) == false){ /**la méthode Verification renvoie faux si le joueur ne peut pas continuer  */
            player.setScoreActuel(motActuel.getScore());   /**  ou le joueur a finie le mot en entier*/
            if (!motActuel.isMotTerminee()){ /** i.e. le jour a echoué  */
                nombreEchecsActuel++;
                if (nombreEchecsActuel >= nombreEchecsMax){
                    sessionTerminee = true;
                    player.AddScore(); /** on sauvegarde le score de la sesion*/
                    player.ResetScore();/** on reinitialise le compteur*/
                }
            }
            if (iterator.hasNext()){/** il reste des mots à deviner*/
                motActuel = iterator.next();
                notifyObservers();
            }
            else {/** le joueur a finie tous les mots*/
                sessionTerminee = true;
                player.AddScore(); /** on sauvegarde le score de la sesion*/
                player.ResetScore();/** on reinitialise le compteur*/

            }

        }
        else player.setScoreActuel(motActuel.getScore());


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
            o.update((Observable) this,motActuel);
        }
    }
}
