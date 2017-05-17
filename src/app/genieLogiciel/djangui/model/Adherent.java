/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.genieLogiciel.djangui.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author kevinash
 */
@Entity
@Table(name = "Adherent")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Adherent.findAll", query = "SELECT a FROM Adherent a")
    , @NamedQuery(name = "Adherent.findByMatricule", query = "SELECT a FROM Adherent a WHERE a.matricule = :matricule")
    , @NamedQuery(name = "Adherent.findByNom", query = "SELECT a FROM Adherent a WHERE a.nom = :nom")
    , @NamedQuery(name = "Adherent.findByNumeroCNI", query = "SELECT a FROM Adherent a WHERE a.numeroCNI = :numeroCNI")
    , @NamedQuery(name = "Adherent.findByAdresse", query = "SELECT a FROM Adherent a WHERE a.adresse = :adresse")
    , @NamedQuery(name = "Adherent.findByDateAdhesion", query = "SELECT a FROM Adherent a WHERE a.dateAdhesion = :dateAdhesion")
    , @NamedQuery(name = "Adherent.findByTelephone", query = "SELECT a FROM Adherent a WHERE a.telephone = :telephone")
    , @NamedQuery(name = "Adherent.findByStatus", query = "SELECT a FROM Adherent a WHERE a.status = :status")})
public class Adherent implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "matricule")
    private Integer matricule;
    @Basic(optional = false)
    @Column(name = "nom")
    private String nom;
    @Basic(optional = false)
    @Column(name = "numeroCNI")
    private int numeroCNI;
    @Basic(optional = false)
    @Column(name = "adresse")
    private String adresse;
    @Basic(optional = false)
    @Column(name = "dateAdhesion")
    @Temporal(TemporalType.DATE)
    private Date dateAdhesion;
    @Basic(optional = false)
    @Column(name = "telephone")
    private String telephone;
    @Basic(optional = false)
    @Column(name = "status")
    private String status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "matriculeAdherent")
    private List<Action> actionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "matriculeAdherent")
    private List<Emprunt> empruntList;

    public Adherent() {
    }

    public Adherent(Integer matricule) {
        this.matricule = matricule;
    }

    public Adherent(Integer matricule, String nom, int numeroCNI, String adresse, Date dateAdhesion, String telephone, String status) {
        this.matricule = matricule;
        this.nom = nom;
        this.numeroCNI = numeroCNI;
        this.adresse = adresse;
        this.dateAdhesion = dateAdhesion;
        this.telephone = telephone;
        this.status = status;
        this.actionList = new ArrayList<>();
        this.empruntList = new ArrayList<>();
    }

    public Integer getMatricule() {
        return matricule;
    }

    public void setMatricule(Integer matricule) {
        this.matricule = matricule;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNumeroCNI() {
        return numeroCNI;
    }

    public void setNumeroCNI(int numeroCNI) {
        this.numeroCNI = numeroCNI;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Date getDateAdhesion() {
        return dateAdhesion;
    }

    public void setDateAdhesion(Date dateAdhesion) {
        this.dateAdhesion = dateAdhesion;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @XmlTransient
    public List<Action> getActionList() {
        return actionList;
    }

    public void setActionList(List<Action> actionList) {
        this.actionList = actionList;
    }

    @XmlTransient
    public List<Emprunt> getEmpruntList() {
        return empruntList;
    }

    public void setEmpruntList(List<Emprunt> empruntList) {
        this.empruntList = empruntList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (matricule != null ? matricule.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Adherent)) {
            return false;
        }
        Adherent other = (Adherent) object;
        if ((this.matricule == null && other.matricule != null) || (this.matricule != null && !this.matricule.equals(other.matricule))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.genieLogiciel.djangui.model.Adherent[ matricule=" + matricule + " ]";
    }
    
}
