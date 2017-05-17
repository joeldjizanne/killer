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
@Table(name = "Fonds")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Fonds.findAll", query = "SELECT f FROM Fonds f")
    , @NamedQuery(name = "Fonds.findByIdFonds", query = "SELECT f FROM Fonds f WHERE f.idFonds = :idFonds")
    , @NamedQuery(name = "Fonds.findByDepotInitial", query = "SELECT f FROM Fonds f WHERE f.depotInitial = :depotInitial")
    , @NamedQuery(name = "Fonds.findBySoldeDisponible", query = "SELECT f FROM Fonds f WHERE f.soldeDisponible = :soldeDisponible")
    , @NamedQuery(name = "Fonds.findByDateDebutOperationsEmprunts", query = "SELECT f FROM Fonds f WHERE f.dateDebutOperationsEmprunts = :dateDebutOperationsEmprunts")
    , @NamedQuery(name = "Fonds.findByDateFinOperationsEmprunts", query = "SELECT f FROM Fonds f WHERE f.dateFinOperationsEmprunts = :dateFinOperationsEmprunts")})
public class Fonds implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idFonds")
    private Integer idFonds;
    @Basic(optional = false)
    @Column(name = "depotInitial")
    private double depotInitial;
    @Basic(optional = false)
    @Column(name = "soldeDisponible")
    private double soldeDisponible;
    @Basic(optional = false)
    @Column(name = "dateDebutOperationsEmprunts")
    @Temporal(TemporalType.DATE)
    private Date dateDebutOperationsEmprunts;
    @Basic(optional = false)
    @Column(name = "dateFinOperationsEmprunts")
    @Temporal(TemporalType.DATE)
    private Date dateFinOperationsEmprunts;

    public Fonds() {
    }

    public Fonds(Integer idFonds) {
        this.idFonds = idFonds;
    }

    public Fonds(Integer idFonds, double depotInitial, double soldeDisponible, Date dateDebutOperationsEmprunts, Date dateFinOperationsEmprunts) {
        this.idFonds = idFonds;
        this.depotInitial = depotInitial;
        this.soldeDisponible = soldeDisponible;
        this.dateDebutOperationsEmprunts = dateDebutOperationsEmprunts;
        this.dateFinOperationsEmprunts = dateFinOperationsEmprunts;
    }

    public Integer getIdFonds() {
        return idFonds;
    }

    public void setIdFonds(Integer idFonds) {
        this.idFonds = idFonds;
    }

    public double getDepotInitial() {
        return depotInitial;
    }

    public void setDepotInitial(double depotInitial) {
        this.depotInitial = depotInitial;
    }

    public double getSoldeDisponible() {
        return soldeDisponible;
    }

    public void setSoldeDisponible(double soldeDisponible) {
        this.soldeDisponible = soldeDisponible;
    }

    public Date getDateDebutOperationsEmprunts() {
        return dateDebutOperationsEmprunts;
    }

    public void setDateDebutOperationsEmprunts(Date dateDebutOperationsEmprunts) {
        this.dateDebutOperationsEmprunts = dateDebutOperationsEmprunts;
    }

    public Date getDateFinOperationsEmprunts() {
        return dateFinOperationsEmprunts;
    }

    public void setDateFinOperationsEmprunts(Date dateFinOperationsEmprunts) {
        this.dateFinOperationsEmprunts = dateFinOperationsEmprunts;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFonds != null ? idFonds.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fonds)) {
            return false;
        }
        Fonds other = (Fonds) object;
        if ((this.idFonds == null && other.idFonds != null) || (this.idFonds != null && !this.idFonds.equals(other.idFonds))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.genieLogiciel.djangui.model.Fonds[ idFonds=" + idFonds + " ]";
    }
    
}
