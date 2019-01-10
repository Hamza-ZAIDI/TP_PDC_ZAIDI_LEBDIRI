package com.company.model.AppConfig;

import com.company.model.mots.WordsGeneratorDAO;
import com.company.model.mots.WordsGeneratorFromFileDAO;
import com.company.model.mots.cases.CasesDifficileFactory;
import com.company.model.mots.cases.CasesFacileFactory;
import com.company.model.mots.cases.CasesFactory;
import com.company.model.mots.cases.CasesMoyensFactory;

public class ParamettreJeux {


    private static ParamettreJeux paramettreJeux;
    private static CasesFactory caseFaciles = new CasesFacileFactory();
    private static CasesFactory caseMoynes = new CasesMoyensFactory();
    private static CasesFactory caseDifficiles = new CasesDifficileFactory();
    private String dataSource = "FILE";
    private ParamettreJeux(){}

    public static ParamettreJeux getInstance(){
        if (paramettreJeux == null) paramettreJeux = new ParamettreJeux();
        return paramettreJeux;
    }
    private Difficulte difficulte = Difficulte.MOYEN;

    public CasesFactory getCasesFactory(){
        CasesFactory casesFactory = null;
        switch (difficulte){
            case FACILE:
                casesFactory = caseFaciles;
                break;
            case MOYEN:
                casesFactory = caseMoynes;
                break;
            case DIFFICILE:
                casesFactory = caseDifficiles;
                break;
            default:
                return casesFactory;

        }
        return casesFactory;
    }

    public WordsGeneratorDAO getWordsGenertorDAO(){
        switch (dataSource){
            case "FILE" :
                return new WordsGeneratorFromFileDAO(FilesPaths.getFilesPahts().getWordsFilePath());
            default:
                return null;
        }
    }
    public void setDifficulte(Difficulte difficulte) {
        this.difficulte = difficulte;
    }

    public int getNbCasesLimites(){
        //TODO
        return 0;
    }
}
