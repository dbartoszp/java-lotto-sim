/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author SuperStudent.EN
 */
@Entity
@Table(name = "numsData")
public class NumberData implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @OneToOne(mappedBy = "numberData")
    private NumberCalculated numberCalculated;

    /**
     * Get the value of numberCalculated
     *
     * @return the value of numberCalculated
     */
    public NumberCalculated getNumberCalculated() {
        return numberCalculated;
    }

    /**
     * Set the value of numberCalculated
     *
     * @param numberCalculated new value of numberCalculated
     */
    public void setNumberCalculated(NumberCalculated numberCalculated) {
        this.numberCalculated = numberCalculated;
    }

    private String RafflesNum;

    /**
     * Get the value of RafflesNum
     *
     * @return the value of RafflesNum
     */
    public String getRafflesNum() {
        return RafflesNum;
    }

    /**
     * Set the value of RafflesNum
     *
     * @param RafflesNum new value of RafflesNum
     */
    public void setRafflesNum(String RafflesNum) {
        this.RafflesNum = RafflesNum;
    }

    private String Number;

    /**
     * Get the value of Number
     *
     * @return the value of Number
     */
    public String getNumber() {
        return Number;
    }

    /**
     * Set the value of Number
     *
     * @param Number new value of Number
     */
    public void setNumber(String Number) {
        this.Number = Number;
    }

    public Long getId() {
        return id;
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
        if (!(object instanceof NumberData)) {
            return false;
        }
        NumberData other = (NumberData) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pl.polsl.entities.NumberData[ id=" + id + " ]";
    }

}
