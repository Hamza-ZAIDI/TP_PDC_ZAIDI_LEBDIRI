package com.company.model;

import com.company.model.mots.DAException;

public interface PlayersHandlerDAO {

    Player findPlayer(String pseudonym) throws DAException;
    void addPlayer(Player player) throws DAException;
}
