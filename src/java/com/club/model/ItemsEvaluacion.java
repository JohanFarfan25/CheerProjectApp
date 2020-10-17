/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.club.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author johan.farfan
 */
@Entity
@Table(name = "items_evaluacion", catalog = "gargolasproject", schema = "")

public class ItemsEvaluacion implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdItems", nullable = false)
    private Integer idItems;
      
  
    @Column(name = "Nombre_Item", nullable = false, length = 18)
    private String nombreItem;
    


    public ItemsEvaluacion() {
    }

    public Integer getIdItems() {
        return idItems;
    }

    public void setIdItems(Integer idItems) {
        this.idItems = idItems;
    }

    public String getNombreItem() {
        return nombreItem;
    }

    public void setNombreItem(String nombreItem) {
        this.nombreItem = nombreItem;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.idItems);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ItemsEvaluacion other = (ItemsEvaluacion) obj;
        if (!Objects.equals(this.idItems, other.idItems)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ItemsEvaluacion{" + "idItems=" + idItems + '}';
    }

    
    
}
