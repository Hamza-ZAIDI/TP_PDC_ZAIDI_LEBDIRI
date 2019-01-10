package com.company.model;

import com.company.model.mots.DAException;


public class PlayersHandler {

    private PlayersHandlerDAO playersHandlerDAO;

    public PlayersHandler(PlayersHandlerDAO playersHandlerDAO) {
        this.playersHandlerDAO = playersHandlerDAO;
    }

    public Player findPlayer(String pseudonyme) throws DAException {
        return playersHandlerDAO.findPlayer(pseudonyme);
    }

    public void addPlayer(Player player) throws DAException, IllegalNicknameException {
        String lettres = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        if (!lettres.contains(player.getPseudonyme().toUpperCase().charAt(0)+"")) throw new IllegalNicknameException();/**si le pseudonyme ne commence pas par une lettre*/
        playersHandlerDAO.addPlayer(player);
    }


}
