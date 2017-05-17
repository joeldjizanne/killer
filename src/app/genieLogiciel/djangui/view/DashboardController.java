/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.genieLogiciel.djangui.view;

import com.jfoenix.controls.JFXButton;
import io.datafx.controller.FXMLController;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javax.annotation.PostConstruct;

/**
 *
 * @author kevinash
 */
@FXMLController("dashboard.fxml")
public class DashboardController {

    @FXML
    private JFXButton backButton;

    @FXML
    private JFXButton acceuilButton;

    @FXML
    private JFXButton infosFondsButton;

    @FXML
    private JFXButton infosExerciceButton;

    @FXML
    private JFXButton listeActionsButton;

    @FXML
    private JFXButton listeAdherentsButton;

    @FXML
    private JFXButton listeEmpruntsButton;

    @FXML
    private JFXButton listeRepartitionsButton;

    @FXML
    private JFXButton quitterButton;

    @FXML
    private AnchorPane centerPane;

    @PostConstruct
    public void init() {
        
    }
}
