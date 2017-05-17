/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.genieLogiciel.djangui;

import app.genieLogiciel.djangui.model.Adherent;
import app.genieLogiciel.djangui.model.Utilisateur;
import app.genieLogiciel.djangui.service.AdherentDAO;
import app.genieLogiciel.djangui.service.UtilisateurDAO;
import app.genieLogiciel.djangui.view.DashboardController;
import app.genieLogiciel.djangui.view.ListeActionsController;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.FlowHandler;
import io.datafx.controller.flow.container.DefaultFlowContainer;
import java.time.Instant;
import java.util.Date;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author kevinashphpp
 */
public class DjanguiApp extends Application {

    @Override
    public void start(Stage primaryStage) throws FlowException {
        Flow flow = new Flow(ListeActionsController.class);
        FlowHandler flowHandler = flow.createHandler();
        StackPane pane = flowHandler.start(new DefaultFlowContainer());
        primaryStage.setScene(new Scene(pane));
        primaryStage.show();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{

        //AdherentDAO.updateAdherent(new Adherent(11, "nene", 85858, "DFDF", Date.from(Instant.now()), "dfdf", "réglo"));
        Adherent a = new Adherent(15, "bmf", 1235, "olembe", Date.from(Instant.now()), "dfsdf", "réglo");
        AdherentDAO.updateAdherent(a);
        
        launch(args);
    }

}
