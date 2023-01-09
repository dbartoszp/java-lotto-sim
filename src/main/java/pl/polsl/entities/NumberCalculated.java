/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author SuperStudent.EN
 */
@Entity
@Table(name = "numCalc")
public class NumberCalculated implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    public Long getId() {
        return id;
    }

    @OneToOne
    @JoinColumn(name = "numData_id", referencedColumnName = "id")
    private NumberData numberData;

    /**
     * Get the value of numberData
     *
     * @return the value of numberData
     */
    public NumberData getNumberData() {
        return numberData;
    }

    /**
     * Set the value of numberData
     *
     * @param numberData new value of numberData
     */
    public void setNumberData(NumberData numberData) {
        this.numberData = numberData;
    }

    private Double Probability;

    /**
     * Get the value of Probability
     *
     * @return the value of Probability
     */
    public Double getProbability() {
        return Probability;
    }

    /**
     * Set the value of Probability
     *
     * @param Probability new value of Probability
     */
    public void setProbability(Double Probability) {
        this.Probability = Probability;
    }

    private int Occurrences;

    /**
     * Get the value of Occurrences
     *
     * @return the value of Occurrences
     */
    public int getOccurrences() {
        return Occurrences;
    }

    /**
     * Set the value of Occurrences
     *
     * @param Occurrences new value of Occurrences
     */
    public void setOccurrences(int Occurrences) {
        this.Occurrences = Occurrences;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof NumberCalculated)) {
            return false;
        }
        NumberCalculated other = (NumberCalculated) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pl.polsl.entities.NumberCalculated[ id=" + id + " ]";
    }

}
