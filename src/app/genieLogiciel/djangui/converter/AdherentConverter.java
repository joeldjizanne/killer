/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.genieLogiciel.djangui.converter;

import app.genieLogiciel.djangui.model.Adherent;
import app.genieLogiciel.djangui.model.AdherentModel;
import app.genieLogiciel.djangui.util.DateConvertionUtil;
import java.time.Month;
import java.util.Date;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author kevinash
 */
public class AdherentConverter {
    public static AdherentModel toProperty(Adherent a) {
        String adresse = a.getAdresse();
        Date dateAdhesion = a.getDateAdhesion();
        Integer matricule = a.getMatricule();
        String nom = a.getNom();
        Integer numeroCNI = a.getNumeroCNI();
        String status = a.getStatus();
        String telephone = a.getTelephone();
        
        return new AdherentModel(matricule, nom, numeroCNI, adresse, DateConvertionUtil.toLocalDate(dateAdhesion), telephone, status);
    }
    
    public static Adherent toPOJO(AdherentModel a) {
        String adresse = a.getAdresse();
        Date dateAdhesion = DateConvertionUtil.toDate(a.getDateAdhesion());
        Integer matricule = a.getMatricule();
        String nom = a.getNom();
        Integer numeroCNI = a.getNumeroCNI();
        String status = a.getStatus();
        String telephone = a.getTelephone();
        
        return new Adherent(matricule, nom, numeroCNI, adresse, dateAdhesion, telephone, status);
    }
    
    public static ObservableList<AdherentModel> toProperties(List<Adherent> adherents) {
        ObservableList<AdherentModel> list = FXCollections.observableArrayList();
        for (Adherent adherent : adherents) {
            list.add(toProperty(adherent));
        }
        return list;
    }
}
