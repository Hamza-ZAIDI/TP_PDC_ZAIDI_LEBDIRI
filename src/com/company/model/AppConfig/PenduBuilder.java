package com.company.model.AppConfig;

import com.company.model.HighScoresDAO;
import com.company.model.Pendu;
import com.company.model.PlayersHandlerDAO;

public interface PenduBuilder {

    PenduBuilder withPlayersHandler(PlayersHandlerDAO playersHandlerDAO);
    PenduBuilder withHighScoresHandler(HighScoresDAO highScoresDAO);

    Pendu build() throws BuildFailedException;
}
