 
package com.club.controller;

import com.club.ejb.InventarioMaterialesFacadeLocal;
import com.club.model.InventarioMateriales;
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
@Named("InventarioMaterialesJpaController")
@ManagedBean(name="inventario_materiales")
@ViewScoped
public class InventarioMaterialesJpaController implements Serializable {

    @EJB
    private InventarioMaterialesFacadeLocal ejbFacade;
    private List<InventarioMateriales> items = null;
    private InventarioMateriales selected;


   
    @PostConstruct
    public void inicial(){
    
       items =  ejbFacade.findAll();
       selected = new InventarioMateriales();
   
        
    }

    public InventarioMaterialesFacadeLocal getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(InventarioMaterialesFacadeLocal ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public List<InventarioMateriales> getItems() {
        return items;
    }

    public void setItems(List<InventarioMateriales> items) {
        this.items = items;
    }

    public InventarioMateriales getSelected() {
        return selected;
    }

    public void setSelected(InventarioMateriales selected) {
        this.selected = selected;
    }

    


    
    
    
    public void registrar(){
        try {
       
            ejbFacade.create(selected);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "¡Aviso!","Material Creado Correctamente"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "¡Aviso!","Error"));
        }
    }
    
    public void leer(InventarioMateriales Seleccion){
         
         selected = Seleccion;
     }
     
     public void modificar(){
         
         ejbFacade.edit(selected);
     }
     
     public void eliminar(){
         
         ejbFacade.remove(selected);
         
           items =  ejbFacade.findAll();
       selected = new InventarioMateriales();
     }
    
}
