package gui;/**
 * Created by Amine on 04/05/2017.
 */

import com.company.model.AppConfig.BuildFailedException;
import com.company.model.AppConfig.FilesPaths;
import com.company.model.AppConfig.PenduBuilder;
import com.company.model.AppConfig.PenduBuilderStandard;
import com.company.model.Pendu;
import com.company.model.PlayersHandlerFileDAO;
import com.company.model.mots.cases.HighScoresFileDAO;
import com.jfoenix.controls.JFXDecorator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {

    PenduBuilder builder;

    /**
     * Les différentes scenes du jeu
     */
    static String HOME = "/resources/fxml/HomeView.fxml";
    static String Home = "/resources/fxml/Home.fxml";
    static final String NEW_SESSION = "/resources/fxml/UserLoginView.fxml";
    static final String CONFIRMATION_DIALOG_BOX = "/resources/fxml/ConfirmationDialogBox.fxml";
    static final String HIGH_SCORES = "/resources/fxml/HighScoresView.fxml";
    static final String OPTIONS = "/resources/fxml/OptionsView.fxml";
    static final String SESSION_VIEW = "/resources/fxml/SessionView.fxml";
    static final String END_SESSION = "/resources/fxml/EndSessionView.fxml";


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        builder = new PenduBuilderStandard();
        try {
            buildPendu(builder);
        } catch (BuildFailedException e) {
            e.printStackTrace();
        }

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(HOME));
        Parent home = loader.load();
        JFXDecorator decorator = new JFXDecorator(primaryStage,home);
        primaryStage.setTitle("Le Pendu");
        primaryStage.setScene(new Scene(decorator, 800, 700));
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    private static Pendu  buildPendu(PenduBuilder builder) throws BuildFailedException {
        return builder
                .withPlayersHandler(new PlayersHandlerFileDAO(FilesPaths.getFilesPahts().getUsersFilePath()))
                .withHighScoresHandler(new HighScoresFileDAO(FilesPaths.getFilesPahts().getHighScorsFilePath()))
                .build();
    }
}
