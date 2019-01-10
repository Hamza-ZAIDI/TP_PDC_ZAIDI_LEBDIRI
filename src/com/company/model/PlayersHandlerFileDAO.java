package com.company.model;

import com.company.model.mots.DAException;

import java.util.HashMap;
import java.io.*;

public class PlayersHandlerFileDAO implements PlayersHandlerDAO {
    private String usersFilePath;
    private HashMap<String, Player> usersHashMap;

    public PlayersHandlerFileDAO(String usersFilePath) {
        this.usersFilePath = usersFilePath;
    }

    @Override
    public Player findPlayer(String pseudonym) throws DAException {
        if (usersHashMap == null) {
            initializeUsersHashMap();
        }
        return usersHashMap.get(pseudonym);
    }

    @Override
    public void addPlayer(Player player) throws DAException {
        if(usersHashMap == null){
            initializeUsersHashMap();
        }

        if (usersHashMap.containsKey(player.getPseudonyme())) {
            usersHashMap.remove(player.getPseudonyme());
        }
        usersHashMap.put(player.getPseudonyme(), player);
        try {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(new File(usersFilePath))));

            objectOutputStream.writeObject(usersHashMap);
            objectOutputStream.close();
        }catch (IOException e){
            throw new DAException("Error while reding users file",e);
        }


    }

    private void initializeUsersHashMap() {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream(new File(usersFilePath))));
            usersHashMap = (HashMap<String, Player>) objectInputStream.readObject();
        } catch (ClassNotFoundException | IOException e) {
            usersHashMap = new HashMap<>();
        }

    }
}
