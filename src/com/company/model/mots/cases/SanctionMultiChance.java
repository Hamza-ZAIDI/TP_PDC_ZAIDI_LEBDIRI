package com.company.model.mots.cases;

import com.company.model.AppConfig.Malus;

public class SanctionMultiChance extends SanctionDecorator{


    public SanctionMultiChance(Case decoratedCase) {
        super(decoratedCase);
        this.malus = Malus.MULTI_CHANCE;
    }

    @Override
    public void tentative(char lettre) {

    }
}
