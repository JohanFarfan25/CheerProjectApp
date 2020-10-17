/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.club.controller;

import com.club.ejb.PrestamoFacadeLocal;
import com.club.model.Prestamo;
import java.io.Serializable;
import java.util.Calendar;
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
@Named("PrestamoJpaController")
@ManagedBean(name="prestamo")
@ViewScoped
public class PrestamoJpaController implements Serializable {

    @EJB
    private PrestamoFacadeLocal ejbFacade;
    private List<Prestamo> items = null;
    private Prestamo selec;
    
     @PostConstruct
    public void inicial(){
    
       items =  ejbFacade.findAll();
       selec = new Prestamo();
   
        
    }

    public PrestamoFacadeLocal getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(PrestamoFacadeLocal ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public List<Prestamo> getItems() {
        return items;
    }

    public void setItems(List<Prestamo> items) {
        this.items = items;
    }

    public Prestamo getSelec() {
        return selec;
    }

    public void setSelec(Prestamo selec) {
        this.selec = selec;
    }

  
    public void registrar(){
        try {
       
            ejbFacade.create(selec);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "¡Aviso!","Solicitud Efectuada"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "¡Aviso!","Error"));
        }
    }
    
       public void devolver(){
        try {
            selec.setFechaDevolucion(Calendar.getInstance().getTime());
            ejbFacade.edit(selec);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "¡Aviso!","Solicitud Efectuada"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "¡Aviso!","Error"));
        }
    }
    
    
    public void leer(Prestamo Seleccion){
         
         selec = Seleccion;
     }
     
     public void modificar(){
         
         ejbFacade.edit(selec);
     }
     
     public void eliminar(){
         
         ejbFacade.remove(selec);
         
           items =  ejbFacade.findAll();
       selec = new Prestamo();
     }
    
    
}
