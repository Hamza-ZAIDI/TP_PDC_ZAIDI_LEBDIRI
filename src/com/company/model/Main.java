package com.company.model;

import com.company.model.AppConfig.FilesPaths;
import com.company.model.AppConfig.ParamettreJeux;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        System.out.println(FilesPaths.getFilesPahts().getUsersFilePath());
    }
}
