/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.genieLogiciel.djangui.model;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.time.LocalDate;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 *
 * @author kevinash
 */
public class ActionModel extends RecursiveTreeObject<ActionModel> {

    private static final long serialVersionUID = 1L;
    private IntegerProperty id;
    private DoubleProperty montant;
    private ObjectProperty<LocalDate> dateSouscription;
    private AdherentModel matriculeAdherent;

    public ActionModel(int id, double montant, LocalDate dateSouscription, AdherentModel matriculeModel) {
        this.id = new SimpleIntegerProperty(id);
        this.montant = new SimpleDoubleProperty(montant);
        this.dateSouscription = new SimpleObjectProperty<LocalDate>(dateSouscription);
        this.matriculeAdherent = matriculeModel;
    }

    public ActionModel(int id, double montant) {
        this.id = new SimpleIntegerProperty(id);
        this.montant = new SimpleDoubleProperty(montant);
        this.dateSouscription = new SimpleObjectProperty<LocalDate>(LocalDate.now());
    }

    public final IntegerProperty idProperty() {
        return this.id;
    }

    public final int getId() {
        return this.idProperty().get();
    }

    public final void setId(final int id) {
        this.idProperty().set(id);
    }

    public final DoubleProperty montantProperty() {
        return this.montant;
    }

    public final double getMontant() {
        return this.montantProperty().get();
    }

    public final void setMontant(final double montant) {
        this.montantProperty().set(montant);
    }

    public final ObjectProperty<LocalDate> dateSouscriptionProperty() {
        return this.dateSouscription;
    }

    public final LocalDate getDateSouscription() {
        return this.dateSouscriptionProperty().get();
    }

    public final void setDateSouscription(final LocalDate dateSouscription) {
        this.dateSouscriptionProperty().set(dateSouscription);
    }

    public AdherentModel getMatriculeAdherent() {
        return matriculeAdherent;
    }
}
