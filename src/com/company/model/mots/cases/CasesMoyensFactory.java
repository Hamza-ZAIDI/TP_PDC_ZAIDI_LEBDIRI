package com.company.model.mots.cases;

import com.company.model.AppConfig.ParamettreJeux;

import java.util.ArrayList;
import java.util.Random;

import static java.lang.Math.abs;

public class CasesMoyensFactory extends CasesFactory {
    @Override
    public ArrayList<Case> createCases(String valeur) {

        ArrayList<Case> ensemblesCases = new ArrayList<>();
        char[] lettre = valeur.toCharArray();// On convertit le String en un tableau de carractères
        int i = 0;
        int choix;
        int nbCasesLimite = 0;
        Case box = null;
        Random random = new Random();
        boolean finished = false;
        while (!finished){// Tant que les carractères ne sont pas converti en case
            choix = abs(random.nextInt())%3;// On choisis le type de la case à instancier selon le résultat
            switch (choix){
                case 0 :
                    box = new MultiChance(lettre[i]);
                    ensemblesCases.add(box);
                    i++;
                  //  this.ensmblesCasesSanctionnables.add((Sanctionnable)box); // Vu que c'est une case sanctionnalbe
                    break;
                case 1 :
                    if(nbCasesLimite < ParamettreJeux.getInstance().getNbCasesLimites()){ // On controle la limite de nombre des cases avant d'insatncier
                        box = new Proposition(lettre[i]);
                        ensemblesCases.add(box);
                        nbCasesLimite++;
                    //    this.ensmblesCasesSanctionnables.add((Sanctionnable) box);
                        i++;
                    }

                    break;
                case 2 :
                    if(nbCasesLimite < ParamettreJeux.getInstance().getNbCasesLimites()){
                        box = new ZeroChance(lettre[i]);
                        i++;
                        nbCasesLimite++;
                        ensemblesCases.add(box);
                    }
                    break;
            }
            if (i == lettre.length) {
                finished = true;
             //  if(ensmblesCasesSanctionnables.size()>NB_CASES_SANCTION) motSanctionnabl = true; // le mot est sanctionnable si la condition est verrifié
            }
        }

        return ensemblesCases;
    }
}
