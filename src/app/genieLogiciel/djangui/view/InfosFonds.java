/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.genieLogiciel.djangui.view;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import io.datafx.controller.FXMLController;
import javafx.fxml.FXML;
import javax.annotation.PostConstruct;

/**
 *
 * @author kevinash
 */
@FXMLController("infosFonds.fxml")
public class InfosFonds {

    @FXML
    private JFXDatePicker dateDebutEmpruntField;

    @FXML
    private JFXDatePicker dateFinEmpruntField;

    @FXML
    private JFXTextField depotInitialField;

    @FXML
    private JFXTextField soldeDisponibleField;

    @PostConstruct
    public void init() {

    }
}
