/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.genieLogiciel.djangui.view;

import app.genieLogiciel.djangui.converter.AdherentConverter;
import app.genieLogiciel.djangui.model.Adherent;
import app.genieLogiciel.djangui.model.AdherentModel;
import app.genieLogiciel.djangui.model.Utilisateur;
import app.genieLogiciel.djangui.service.AdherentDAO;
import app.genieLogiciel.djangui.util.DateUtil;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import io.datafx.controller.FXMLController;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.function.Predicate;
import java.util.logging.Logger;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import javax.annotation.PostConstruct;

/**
 *
 * @author kevinash
 */
@FXMLController("listeAdherents.fxml")
public class ListeActionsController {

    @FXML
    private JFXTextField rechercherField;

    @FXML
    private JFXTreeTableView<AdherentModel> treeView;

    @FXML
    private JFXButton appliquerButton;
    
    @FXML
    private JFXButton ajouterButton;

    @FXML
    private JFXComboBox<Label> actionButton;

    @PostConstruct
    public void init() {

        double COL_WIDTH = 1146.0 / 7.0;

        JFXTreeTableColumn<AdherentModel, String> matriculeCol = new JFXTreeTableColumn<>("matricule");
        JFXTreeTableColumn<AdherentModel, String> nomCol = new JFXTreeTableColumn<>("nom");
        JFXTreeTableColumn<AdherentModel, String> numeroCNI = new JFXTreeTableColumn<>("numero de CNI");
        JFXTreeTableColumn<AdherentModel, String> adresseCol = new JFXTreeTableColumn<>("adresse");
        JFXTreeTableColumn<AdherentModel, String> dateAdhesionCol = new JFXTreeTableColumn<>("date adhesion");
        JFXTreeTableColumn<AdherentModel, String> telephoneCol = new JFXTreeTableColumn<>("telephone");
        JFXTreeTableColumn<AdherentModel, String> statusCol = new JFXTreeTableColumn<>("status");

        matriculeCol.setPrefWidth(COL_WIDTH);
        nomCol.setPrefWidth(COL_WIDTH);
        numeroCNI.setPrefWidth(COL_WIDTH);
        adresseCol.setPrefWidth(COL_WIDTH);
        dateAdhesionCol.setPrefWidth(COL_WIDTH);
        telephoneCol.setPrefWidth(COL_WIDTH);
        statusCol.setPrefWidth(COL_WIDTH);

        matriculeCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<AdherentModel, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<AdherentModel, String> param) {
                IntegerProperty val = param.getValue().getValue().matriculeProperty();
                return new SimpleStringProperty(val.getValue() + "");
            }
        });

        nomCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<AdherentModel, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<AdherentModel, String> param) {
                return param.getValue().getValue().nomProperty();
            }
        });

        adresseCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<AdherentModel, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<AdherentModel, String> param) {
                return param.getValue().getValue().adresseProperty();
            }
        });

        statusCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<AdherentModel, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<AdherentModel, String> param) {
                return param.getValue().getValue().statusProperty();
            }
        });

        telephoneCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<AdherentModel, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<AdherentModel, String> param) {
                return param.getValue().getValue().telephoneProperty();
            }
        });

        numeroCNI.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<AdherentModel, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<AdherentModel, String> param) {
                IntegerProperty val = param.getValue().getValue().numeroCNIProperty();
                return new SimpleStringProperty(val.getValue() + "");
            }
        });

        dateAdhesionCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<AdherentModel, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<AdherentModel, String> param) {
                LocalDate val = param.getValue().getValue().getDateAdhesion();
                return new SimpleStringProperty(DateUtil.format(val));
            }
        });

        ObservableList<AdherentModel> adherents = AdherentConverter.toProperties(AdherentDAO.getAllAdherents());

        TreeItem<AdherentModel> root = new RecursiveTreeItem<AdherentModel>(adherents, RecursiveTreeObject::getChildren);
        treeView.getColumns().setAll(matriculeCol, nomCol, numeroCNI, adresseCol, dateAdhesionCol, telephoneCol, statusCol);
        treeView.setRoot(root);
        treeView.setShowRoot(false);

        rechercherField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                treeView.setPredicate(new Predicate<TreeItem<AdherentModel>>() {
                    @Override
                    public boolean test(TreeItem<AdherentModel> t) {
                        String f = t.getValue().getTelephone()+"";
                        String f2 = t.getValue().getMatricule()+"";
                        String f3 = t.getValue().getNumeroCNI()+"";
                        Boolean flag = t.getValue().getNom().contains(newValue)
                                | t.getValue().getAdresse().contains(newValue)
                                | t.getValue().getStatus().contains(newValue)
                                | f.contains(newValue)
                                | f2.contains(newValue)
                                | f3.contains(newValue);
                        return flag;
                    }
                });
            }
        });
        
        
        actionButton.getItems().addAll(new Label("suspendre"), new Label("exclure"));
        appliquerButton.setOnMouseClicked((MouseEvent event) -> {
            Label selectedItem = actionButton.getSelectionModel().getSelectedItem();
            if(selectedItem == null) {
                System.out.println("no item selected!");
                return;
            }
            
            
            AdherentModel selectedAdherent = treeView.getSelectionModel().getSelectedItem().getValue();
            
            switch (selectedItem.getText()) {
                case "suspendre":
                    if (!selectedAdherent.getStatus().equals("réglo")) {
                        System.out.println("vous ne pouvez suspendre un adhérent "+selectedAdherent.getStatus());
                    } else {
                        selectedAdherent.setStatus("suspendu");
                        Adherent persist = AdherentConverter.toPOJO(selectedAdherent);
                        
                        
                    }
                    break;
                case "exclure":
                    System.out.println("exclure");
                    break;
            }
        });
        
        treeView.getSelectionModel().getTreeTableView().addEventFilter(MouseEvent.MOUSE_PRESSED, ((event) -> {
            TreeItem<AdherentModel> selectedItem = treeView.getSelectionModel().getSelectedItem();
            if(selectedItem == null) 
                return;
            
            AdherentModel selectedAdherent = selectedItem.getValue();
            actionButton.setDisable(selectedAdherent == null);
            appliquerButton.setDisable(selectedAdherent == null);
            
        }));
    }
    private static final Logger LOG = Logger.getLogger(ListeActionsController.class.getName());
}
