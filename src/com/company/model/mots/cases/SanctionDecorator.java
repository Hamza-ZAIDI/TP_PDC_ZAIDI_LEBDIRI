package com.company.model.mots.cases;

import com.company.model.AppConfig.Malus;

public abstract class SanctionDecorator extends Case{
    protected Malus malus;
    protected Case decoratedCase;

    public SanctionDecorator(Case decoratedCase) {
        this.decoratedCase = decoratedCase;
    }
}
