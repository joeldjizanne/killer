/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.genieLogiciel.djangui.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kevinash
 */
@Entity
@Table(name = "Emprunt")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Emprunt.findAll", query = "SELECT e FROM Emprunt e")
    , @NamedQuery(name = "Emprunt.findByIdEmprunt", query = "SELECT e FROM Emprunt e WHERE e.idEmprunt = :idEmprunt")
    , @NamedQuery(name = "Emprunt.findByMontant", query = "SELECT e FROM Emprunt e WHERE e.montant = :montant")
    , @NamedQuery(name = "Emprunt.findByInterets", query = "SELECT e FROM Emprunt e WHERE e.interets = :interets")
    , @NamedQuery(name = "Emprunt.findByDateEmprunt", query = "SELECT e FROM Emprunt e WHERE e.dateEmprunt = :dateEmprunt")
    , @NamedQuery(name = "Emprunt.findByDateDelais", query = "SELECT e FROM Emprunt e WHERE e.dateDelais = :dateDelais")
    , @NamedQuery(name = "Emprunt.findByDateRemboursement", query = "SELECT e FROM Emprunt e WHERE e.dateRemboursement = :dateRemboursement")
    , @NamedQuery(name = "Emprunt.findByStatus", query = "SELECT e FROM Emprunt e WHERE e.status = :status")})
public class Emprunt implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idEmprunt")
    private Integer idEmprunt;
    @Basic(optional = false)
    @Column(name = "montant")
    private double montant;
    @Basic(optional = false)
    @Column(name = "interets")
    private double interets;
    @Basic(optional = false)
    @Column(name = "dateEmprunt")
    @Temporal(TemporalType.DATE)
    private Date dateEmprunt;
    @Basic(optional = false)
    @Column(name = "dateDelais")
    @Temporal(TemporalType.DATE)
    private Date dateDelais;
    @Basic(optional = false)
    @Column(name = "dateRemboursement")
    @Temporal(TemporalType.DATE)
    private Date dateRemboursement;
    @Basic(optional = false)
    @Column(name = "status")
    private String status;
    @JoinColumn(name = "matriculeAdherent", referencedColumnName = "matricule")
    @ManyToOne(optional = false)
    private Adherent matriculeAdherent;

    public Emprunt() {
    }

    public Emprunt(Integer idEmprunt) {
        this.idEmprunt = idEmprunt;
    }

    public Emprunt(Integer idEmprunt, double montant, double interets, Date dateEmprunt, Date dateDelais, Date dateRemboursement, String status) {
        this.idEmprunt = idEmprunt;
        this.montant = montant;
        this.interets = interets;
        this.dateEmprunt = dateEmprunt;
        this.dateDelais = dateDelais;
        this.dateRemboursement = dateRemboursement;
        this.status = status;
    }

    public Integer getIdEmprunt() {
        return idEmprunt;
    }

    public void setIdEmprunt(Integer idEmprunt) {
        this.idEmprunt = idEmprunt;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public double getInterets() {
        return interets;
    }

    public void setInterets(double interets) {
        this.interets = interets;
    }

    public Date getDateEmprunt() {
        return dateEmprunt;
    }

    public void setDateEmprunt(Date dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }

    public Date getDateDelais() {
        return dateDelais;
    }

    public void setDateDelais(Date dateDelais) {
        this.dateDelais = dateDelais;
    }

    public Date getDateRemboursement() {
        return dateRemboursement;
    }

    public void setDateRemboursement(Date dateRemboursement) {
        this.dateRemboursement = dateRemboursement;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Adherent getMatriculeAdherent() {
        return matriculeAdherent;
    }

    public void setMatriculeAdherent(Adherent matriculeAdherent) {
        this.matriculeAdherent = matriculeAdherent;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEmprunt != null ? idEmprunt.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Emprunt)) {
            return false;
        }
        Emprunt other = (Emprunt) object;
        if ((this.idEmprunt == null && other.idEmprunt != null) || (this.idEmprunt != null && !this.idEmprunt.equals(other.idEmprunt))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.genieLogiciel.djangui.model.Emprunt[ idEmprunt=" + idEmprunt + " ]";
    }
    
}
