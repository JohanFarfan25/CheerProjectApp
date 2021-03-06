/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.club.controller;

import com.club.ejb.ItemsEvaluacionFacadeLocal;
import com.club.ejb.PlanDeEntrenamientoFacadeLocal;
import com.club.ejb.Semana1FacadeLocal;
import com.club.model.ItemsEvaluacion;
import com.club.model.PlanDeEntrenamiento;
import com.club.model.Semana1;
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
@Named("Semana1JpaController")
@ManagedBean(name="semana_1")
@ViewScoped
public class Semana1JpaController implements Serializable {

    @EJB
    private Semana1FacadeLocal ejbFacade;
       
    @EJB
    private PlanDeEntrenamientoFacadeLocal ejbPlan;
    
    @EJB
    private ItemsEvaluacionFacadeLocal ejbItemsEvalu;
        
    
    private List<Semana1> items = null;
    private List<PlanDeEntrenamiento> Plan;
    private List<ItemsEvaluacion> ItemsE;
    
    private Semana1 selected;
    
    
    @PostConstruct
    public void inicial(){
    
        
       selected = new Semana1();
       
       selected.setIdPlan(new PlanDeEntrenamiento());
       selected.setIdItems(new ItemsEvaluacion());
       
       items =  ejbFacade.findAll();
       Plan=ejbPlan.findAll();
       ItemsE=ejbItemsEvalu.findAll();
       
    
    }

    public Semana1FacadeLocal getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(Semana1FacadeLocal ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public List<Semana1> getItems() {
        return items;
    }

    public void setItems(List<Semana1> items) {
        this.items = items;
    }

    public Semana1 getSelected() {
        return selected;
    }

    public void setSelected(Semana1 selected) {
        this.selected = selected;
    }

    public PlanDeEntrenamientoFacadeLocal getEjbPlan() {
        return ejbPlan;
    }

    public void setEjbPlan(PlanDeEntrenamientoFacadeLocal ejbPlan) {
        this.ejbPlan = ejbPlan;
    }

    public List<PlanDeEntrenamiento> getPlan() {
        return Plan;
    }

    public void setPlan(List<PlanDeEntrenamiento> Plan) {
        this.Plan = Plan;
    }

    public ItemsEvaluacionFacadeLocal getEjbItemsEvalu() {
        return ejbItemsEvalu;
    }

    public void setEjbItemsEvalu(ItemsEvaluacionFacadeLocal ejbItemsEvalu) {
        this.ejbItemsEvalu = ejbItemsEvalu;
    }

    public List<ItemsEvaluacion> getItemsE() {
        return ItemsE;
    }

    public void setItemsE(List<ItemsEvaluacion> ItemsE) {
        this.ItemsE = ItemsE;
    }
    
    
    
     public void registrar(){
        try {
       
            ejbFacade.create(selected);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "¡Aviso!","Calificación Ingresada"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "¡Aviso!","Error"));
        }
    }
    
    public void leer(Semana1 Seleccion){
         
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
       selected = new Semana1();
     }
    
}
