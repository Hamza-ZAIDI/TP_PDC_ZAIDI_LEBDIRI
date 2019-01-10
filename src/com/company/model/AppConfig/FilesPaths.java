package com.company.model.AppConfig;

public class FilesPaths {
    private String usersFilePath = "C:\\Users\\hamza\\IdeaProjects\\TP_POO_Guerras_Zaidi_G12\\users.dat";
    private String wordsFilePath = "C:\\Users\\hamza\\IdeaProjects\\TP_POO_Guerras_Zaidi_G12\\mots.poo";
    private String highScorsFilePath = "C:\\Users\\hamza\\IdeaProjects\\TP_POO_Guerras_Zaidi_G12\\highScors.dat";
    private static FilesPaths filesPaths;

    private FilesPaths(){}

    public static FilesPaths getFilesPahts(){
        if(filesPaths == null){
            filesPaths = new FilesPaths();
        }

        return filesPaths;
    }

    public String getUsersFilePath() {
        return usersFilePath;
    }

    public String getWordsFilePath() {
        return wordsFilePath;
    }

    public String getHighScorsFilePath() {
        return highScorsFilePath;
    }
}
