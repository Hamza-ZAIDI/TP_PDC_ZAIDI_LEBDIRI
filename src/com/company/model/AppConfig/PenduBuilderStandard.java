package com.company.model.AppConfig;

import com.company.model.*;

public class PenduBuilderStandard implements PenduBuilder{

    private PlayersHandler playersHandler;
    private HighScoresHandler scoresHandler;
    @Override
    public PenduBuilder withPlayersHandler(PlayersHandlerDAO playersHandlerDAO) {
        playersHandler = new PlayersHandler(playersHandlerDAO);
    return this;
    }

    @Override
    public PenduBuilder withHighScoresHandler(HighScoresDAO highScoresDAO) {
        this.scoresHandler = new HighScoresHandler(highScoresDAO);
        return this;
    }

    @Override
    public Pendu build() throws BuildFailedException {
        Pendu.getPendu().setPlayersHandler(this.playersHandler);
        Pendu.getPendu().setHighScoresHandler(this.scoresHandler);
        if(Pendu.getPendu() == null) throw new BuildFailedException();
        else return Pendu.getPendu();
     }
}
