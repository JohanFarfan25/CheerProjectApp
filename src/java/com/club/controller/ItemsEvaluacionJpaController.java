/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.club.controller;

import com.club.ejb.ItemsEvaluacionFacadeLocal;
import com.club.model.ItemsEvaluacion;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author johan.farfan
 */

@Named("ItemsEvaluacionJpaController")
@ManagedBean(name="items_evaluacion")
@ViewScoped
public class ItemsEvaluacionJpaController implements Serializable {

    @EJB
    private ItemsEvaluacionFacadeLocal ejbFacade;
    private List<ItemsEvaluacion> items = null;
    private ItemsEvaluacion selected;
    
    @PostConstruct
    public void inicial(){
    
       items =  ejbFacade.findAll();
       selected = new ItemsEvaluacion();
    
    }

    public ItemsEvaluacionFacadeLocal getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(ItemsEvaluacionFacadeLocal ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public List<ItemsEvaluacion> getItems() {
        return items;
    }

    public void setItems(List<ItemsEvaluacion> items) {
        this.items = items;
    }

    public ItemsEvaluacion getSelected() {
        return selected;
    }

    public void setSelected(ItemsEvaluacion selected) {
        this.selected = selected;
    }
    
     public void registrar(){
        try {
       
            ejbFacade.create(selected);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "¡Aviso!","Item De Evaluación Creado Correctamente"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "¡Aviso!","Error"));
        }
    }
    
    public void leer(ItemsEvaluacion Seleccion){
         
         selected = Seleccion;
     }
     
     public void modificar(){
             
          try {
       
            ejbFacade.edit(selected);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "¡Aviso!","Categoría Editada Correctamente"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "¡Aviso!","Error"));
        }
     }
     
     public void eliminar(){
         
         ejbFacade.remove(selected);
         
           items =  ejbFacade.findAll();
       selected = new ItemsEvaluacion();
     }
}
