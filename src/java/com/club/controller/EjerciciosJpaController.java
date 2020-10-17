/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.club.controller;

import com.club.ejb.EjerciciosFacadeLocal;
import com.club.model.Ejercicios;
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
@Named("EjerciciosJpaController")
@ManagedBean(name="ejercicios")
@ViewScoped
public class EjerciciosJpaController implements Serializable {

    @EJB
    private EjerciciosFacadeLocal ejbFacade;
    private List<Ejercicios> items = null;
    private Ejercicios selected;
    
    
    @PostConstruct
    public void inicial(){
    
       items =  ejbFacade.findAll();
       selected = new Ejercicios();
    
    }

    public EjerciciosFacadeLocal getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(EjerciciosFacadeLocal ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public List<Ejercicios> getItems() {
        return items;
    }

    public void setItems(List<Ejercicios> items) {
        this.items = items;
    }

    public Ejercicios getSelected() {
        return selected;
    }

    public void setSelected(Ejercicios selected) {
        this.selected = selected;
    }
    
     public void registrar(){
        try {
       
            ejbFacade.create(selected);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "¡Aviso!","Ejercicio Ingresado Correctamente"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "¡Aviso!","Error"));
        }
    }
    
    public void leer(Ejercicios Seleccion){
         
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
       selected = new Ejercicios();
     }
}
