package com.company.model.mots;
;
import com.company.model.AppConfig.ParamettreJeux;
import com.company.model.mots.cases.*;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import static java.lang.Math.abs;

/**
 * Created by Amine on 17/04/2017.
 */
public class Mot{

    private final int NB_CASES_SANCTION = 5;
    private final int NB_CASES_LIMITES = 3;

    private Indication indication;
    private String valeur;
    private ArrayList<Case> ensemblesCases = new ArrayList<>();
    private ArrayList<Sanctionnable> ensmblesCasesSanctionnables = new ArrayList<>();
    private int score = 0;
    private boolean motTerminee;
    private boolean motSanctionnabl;
    private int nbSuccess;
    private boolean correct = false;
    private ArrayList<Observer> observers = new ArrayList<>();
   private CasesFactory factory;

    public boolean isMotSanctionnabl() {
        return motSanctionnabl;
    }

    public Mot(Indication indication, String valeur) {
        this.indication = indication;
        this.valeur = valeur;
        factory = ParamettreJeux.getInstance().getCasesFactory();
    }

    public boolean isCorrect() {
        return correct;
    }

    public boolean isMotTerminee() {
        return motTerminee;
    }

    public int getScore() {
        return score;
    }

    public ArrayList<Case> getEnsemblesCases() {
        return ensemblesCases;
    }

    public String getValeur() {
        return valeur;
    }


    /*
     *Met à jour le score d'un a l'issue d'un tentative
     */

    private void updateScore(Case box){
        this.score += indication.getCoefition()*box.getScore();
    }
    /*
    * Générer les cases formant les mots et met à jours l'attribut motSanctionnabl si nécessaire
     */
    public void genererCases(){

        this.ensemblesCases = factory.createCases(this.valeur);

    }

    /**
     * Verrifie la tentative de l'utilisateur sur une case choisis du mot
     * @param c le carractère entré pa l'utilisateur
     * @param index le rang de la case dans le mot
     * @return false si le joueur ne peut pas continuer
     */
    public boolean Verification(char c, int index) {
        boolean stop = false;
        Case box = ensemblesCases.get(index);
        box.tentative(c);
        stop = ensemblesCases.get(index).isFail();//On s'arrete si le joueur échoue
        if (stop) {
            correct = false;
        }
        if (box.isSuceces()) nbSuccess++;// si le joueur entre le bon carractère on incrémente le nombre de succes
        System.out.println("nbSuccess = " + nbSuccess);
        if (nbSuccess == ensemblesCases.size()) {// si le joueur réussi tous les case
            motTerminee = true; // donc le mot est terminé
            System.out.println("Mot termine");
            correct = true;
            stop = true; // le joueur ne peut pas continuer
        }
        updateScore(box);
        System.out.println("score = "+this.score);
        return !stop;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Mot)) return false;

        Mot mot = (Mot) o;

        return valeur != null ? valeur.equals(mot.valeur) : mot.valeur == null;
    }

    @Override
    public int hashCode() {
        return valeur.hashCode();
    }

    @Override
    public String toString() {
        return valeur  ;
    }

    public Indication getIndication() {
        return indication;
    }

}
