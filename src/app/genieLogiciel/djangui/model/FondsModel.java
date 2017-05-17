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
public class FondsModel extends RecursiveTreeObject<FondsModel> {

    private IntegerProperty idFonds;
    private DoubleProperty depotInitial;
    private DoubleProperty soldeDisponible;
    private ObjectProperty<LocalDate> dateDebutOperationsEmprunts;
    private ObjectProperty<LocalDate> dateFinOperationsEmprunts;

    public FondsModel(Integer idFonds, double depotInitial, double soldeDisponible, LocalDate dateDebutOperationsEmprunts,
            LocalDate dateFinOperationsEmprunts) {
        super();
        this.idFonds = new SimpleIntegerProperty(idFonds);
        this.depotInitial = new SimpleDoubleProperty(depotInitial);
        this.soldeDisponible = new SimpleDoubleProperty(soldeDisponible);
        this.dateDebutOperationsEmprunts = new SimpleObjectProperty<>(dateDebutOperationsEmprunts);
        this.dateFinOperationsEmprunts = new SimpleObjectProperty<>(dateFinOperationsEmprunts);;
    }

    public final IntegerProperty idFondsProperty() {
        return this.idFonds;
    }

    public final int getIdFonds() {
        return this.idFondsProperty().get();
    }

    public final void setIdFonds(final int idFonds) {
        this.idFondsProperty().set(idFonds);
    }

    public final DoubleProperty depotInitialProperty() {
        return this.depotInitial;
    }

    public final double getDepotInitial() {
        return this.depotInitialProperty().get();
    }

    public final void setDepotInitial(final double depotInitial) {
        this.depotInitialProperty().set(depotInitial);
    }

    public final DoubleProperty soldeDisponibleProperty() {
        return this.soldeDisponible;
    }

    public final double getSoldeDisponible() {
        return this.soldeDisponibleProperty().get();
    }

    public final void setSoldeDisponible(final double soldeDisponible) {
        this.soldeDisponibleProperty().set(soldeDisponible);
    }

    public final ObjectProperty<LocalDate> dateDebutOperationsEmpruntsProperty() {
        return this.dateDebutOperationsEmprunts;
    }

    public final LocalDate getDateDebutOperationsEmprunts() {
        return this.dateDebutOperationsEmpruntsProperty().get();
    }

    public final void setDateDebutOperationsEmprunts(final LocalDate dateDebutOperationsEmprunts) {
        this.dateDebutOperationsEmpruntsProperty().set(dateDebutOperationsEmprunts);
    }

    public final ObjectProperty<LocalDate> dateFinOperationsEmpruntsProperty() {
        return this.dateFinOperationsEmprunts;
    }

    public final LocalDate getDateFinOperationsEmprunts() {
        return this.dateFinOperationsEmpruntsProperty().get();
    }

    public final void setDateFinOperationsEmprunts(final LocalDate dateFinOperationsEmprunts) {
        this.dateFinOperationsEmpruntsProperty().set(dateFinOperationsEmprunts);
    }

}
