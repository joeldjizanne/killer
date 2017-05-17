/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.genieLogiciel.djangui.model;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.time.LocalDate;
import java.util.List;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author kevinash
 */
public class AdherentModel extends RecursiveTreeObject<AdherentModel> {

    private IntegerProperty matricule;
    private StringProperty nom;
    private IntegerProperty numeroCNI;
    private StringProperty adresse;
    private SimpleObjectProperty<LocalDate> dateAdhesion;
    private StringProperty telephone;
    private StringProperty status;
    private List<ActionModel> actionList;
    private List<EmpruntModel> empruntList;

    public AdherentModel(Integer matricule, String nom, int numeroCNI, String adresse, LocalDate dateAdhesion,
            String telephone, String status) {
        super();
        this.matricule = new SimpleIntegerProperty(matricule);
        this.nom = new SimpleStringProperty(nom);
        this.numeroCNI = new SimpleIntegerProperty(numeroCNI);;
        this.adresse = new SimpleStringProperty(adresse);;
        this.dateAdhesion = new SimpleObjectProperty<>(dateAdhesion);;
        this.telephone = new SimpleStringProperty(telephone);;
        this.status = new SimpleStringProperty(status);;
    }

    public AdherentModel(Integer matricule, String nom, int numeroCNI, String adresse,
            String telephone, String status) {
        super();
        this.matricule = new SimpleIntegerProperty(matricule);
        this.nom = new SimpleStringProperty(nom);
        this.numeroCNI = new SimpleIntegerProperty(numeroCNI);;
        this.adresse = new SimpleStringProperty(adresse);;
        this.dateAdhesion = new SimpleObjectProperty<>(LocalDate.now());;
        this.telephone = new SimpleStringProperty(telephone);;
        this.status = new SimpleStringProperty(status);;
    }

    public final IntegerProperty matriculeProperty() {
        return this.matricule;
    }

    public final int getMatricule() {
        return this.matriculeProperty().get();
    }

    public final void setMatricule(final int matricule) {
        this.matriculeProperty().set(matricule);
    }

    public final StringProperty nomProperty() {
        return this.nom;
    }

    public final String getNom() {
        return this.nomProperty().get();
    }

    public final void setNom(final String nom) {
        this.nomProperty().set(nom);
    }

    public final IntegerProperty numeroCNIProperty() {
        return this.numeroCNI;
    }

    public final int getNumeroCNI() {
        return this.numeroCNIProperty().get();
    }

    public final void setNumeroCNI(final int numeroCNI) {
        this.numeroCNIProperty().set(numeroCNI);
    }

    public final StringProperty adresseProperty() {
        return this.adresse;
    }

    public final String getAdresse() {
        return this.adresseProperty().get();
    }

    public final void setAdresse(final String adresse) {
        this.adresseProperty().set(adresse);
    }

    public final SimpleObjectProperty<LocalDate> dateAdhesionProperty() {
        return this.dateAdhesion;
    }

    public final LocalDate getDateAdhesion() {
        return this.dateAdhesionProperty().get();
    }

    public final void setDateAdhesion(final LocalDate dateAdhesion) {
        this.dateAdhesionProperty().set(dateAdhesion);
    }

    public final StringProperty telephoneProperty() {
        return this.telephone;
    }

    public final String getTelephone() {
        return this.telephoneProperty().get();
    }

    public final void setTelephone(final String telephone) {
        this.telephoneProperty().set(telephone);
    }

    public final StringProperty statusProperty() {
        return this.status;
    }

    public final String getStatus() {
        return this.statusProperty().get();
    }

    public final void setStatus(final String status) {
        this.statusProperty().set(status);
    }

    public List<ActionModel> getActionList() {
        return actionList;
    }

    public void setActionList(List<ActionModel> actionList) {
        this.actionList = actionList;
    }

    public List<EmpruntModel> getEmpruntList() {
        return empruntList;
    }

    public void setEmpruntList(List<EmpruntModel> empruntList) {
        this.empruntList = empruntList;
    }
    
    

}
