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
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author kevinash
 */
public class EmpruntModel extends RecursiveTreeObject<EmpruntModel> {
    private IntegerProperty idEmprunt;
    private DoubleProperty montant;
    private DoubleProperty interets;
    private ObjectProperty<LocalDate> dateEmprunt;
    private ObjectProperty<LocalDate> dateDelais;
    private ObjectProperty<LocalDate> dateRemboursement;
    private StringProperty status;
    private AdherentModel matriculeAdherent;
    
    
	public EmpruntModel(Integer idEmprunt, double montant, double interets, LocalDate dateEmprunt, LocalDate dateDelais,
			LocalDate dateRemboursement, String status, AdherentModel matriculeAdherent) {
		
		super();
		this.idEmprunt = new SimpleIntegerProperty(idEmprunt);
		this.montant = new SimpleDoubleProperty(montant);
		this.interets = new SimpleDoubleProperty(interets);
		this.dateEmprunt = new SimpleObjectProperty<>(dateEmprunt);
		this.dateDelais = new SimpleObjectProperty<>(dateDelais);
		this.dateRemboursement = new SimpleObjectProperty<>(dateRemboursement);
		this.status = new SimpleStringProperty(status);
		this.matriculeAdherent = matriculeAdherent;
	}


	public final IntegerProperty idEmpruntProperty() {
		return this.idEmprunt;
	}
	


	public final int getIdEmprunt() {
		return this.idEmpruntProperty().get();
	}
	


	public final void setIdEmprunt(final int idEmprunt) {
		this.idEmpruntProperty().set(idEmprunt);
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
	


	public final DoubleProperty interetsProperty() {
		return this.interets;
	}
	


	public final double getInterets() {
		return this.interetsProperty().get();
	}
	


	public final void setInterets(final double interets) {
		this.interetsProperty().set(interets);
	}
	


	public final ObjectProperty<LocalDate> dateEmpruntProperty() {
		return this.dateEmprunt;
	}
	


	public final LocalDate getDateEmprunt() {
		return this.dateEmpruntProperty().get();
	}
	


	public final void setDateEmprunt(final LocalDate dateEmprunt) {
		this.dateEmpruntProperty().set(dateEmprunt);
	}
	


	public final ObjectProperty<LocalDate> dateDelaisProperty() {
		return this.dateDelais;
	}
	


	public final LocalDate getDateDelais() {
		return this.dateDelaisProperty().get();
	}
	


	public final void setDateDelais(final LocalDate dateDelais) {
		this.dateDelaisProperty().set(dateDelais);
	}
	


	public final ObjectProperty<LocalDate> dateRemboursementProperty() {
		return this.dateRemboursement;
	}
	


	public final LocalDate getDateRemboursement() {
		return this.dateRemboursementProperty().get();
	}
	


	public final void setDateRemboursement(final LocalDate dateRemboursement) {
		this.dateRemboursementProperty().set(dateRemboursement);
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
	
}
