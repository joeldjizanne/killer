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
@Table(name = "Action")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Action.findAll", query = "SELECT a FROM Action a")
    , @NamedQuery(name = "Action.findById", query = "SELECT a FROM Action a WHERE a.id = :id")
    , @NamedQuery(name = "Action.findByMontant", query = "SELECT a FROM Action a WHERE a.montant = :montant")
    , @NamedQuery(name = "Action.findByDateSouscription", query = "SELECT a FROM Action a WHERE a.dateSouscription = :dateSouscription")})
public class Action implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "montant")
    private double montant;
    @Basic(optional = false)
    @Column(name = "dateSouscription")
    @Temporal(TemporalType.DATE)
    private Date dateSouscription;
    @JoinColumn(name = "matriculeAdherent", referencedColumnName = "matricule")
    @ManyToOne(optional = false)
    private Adherent matriculeAdherent;

    public Action() {
    }

    public Action(Integer id) {
        this.id = id;
    }

    public Action(Integer id, double montant, Date dateSouscription) {
        this.id = id;
        this.montant = montant;
        this.dateSouscription = dateSouscription;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public Date getDateSouscription() {
        return dateSouscription;
    }

    public void setDateSouscription(Date dateSouscription) {
        this.dateSouscription = dateSouscription;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Action)) {
            return false;
        }
        Action other = (Action) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.genieLogiciel.djangui.model.Action[ id=" + id + " ]";
    }
    
}
