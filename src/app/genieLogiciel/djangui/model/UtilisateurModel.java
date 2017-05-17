/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.genieLogiciel.djangui.model;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author kevinash
 */
public class UtilisateurModel extends RecursiveTreeObject<AdherentModel> {
    private StringProperty username;
    private StringProperty password;
    
	public UtilisateurModel(String username, String password) {
		super();
		this.username = new SimpleStringProperty(username);
		this.password = new SimpleStringProperty(password);
	}

	public final StringProperty usernameProperty() {
		return this.username;
	}
	

	public final String getUsername() {
		return this.usernameProperty().get();
	}
	

	public final void setUsername(final String username) {
		this.usernameProperty().set(username);
	}
	

	public final StringProperty passwordProperty() {
		return this.password;
	}
	

	public final String getPassword() {
		return this.passwordProperty().get();
	}
	

	public final void setPassword(final String password) {
		this.passwordProperty().set(password);
	}
	
	
}
