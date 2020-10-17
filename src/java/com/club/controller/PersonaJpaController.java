/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.club.controller;

import com.club.ejb.PersonaFacadeLocal;
import com.club.model.Persona;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author johan.farfan
 */
@Named
@ViewScoped
public class PersonaJpaController implements Serializable {

    @EJB
    private PersonaFacadeLocal personaEJB;
    private Persona persona;
    private List<Persona> lista;

    
     @PostConstruct
    public void init(){
        
        persona = new Persona();
        lista =  personaEJB.findAll();
        
    }

    public PersonaFacadeLocal getPersonaEJB() {
        return personaEJB;
    }

    public void setPersonaEJB(PersonaFacadeLocal personaEJB) {
        this.personaEJB = personaEJB;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public List<Persona> getLista() {
        return lista;
    }

    public void setLista(List<Persona> lista) {
        this.lista = lista;
    }
    
     public void leer(Persona Seleccion){
         
         persona = Seleccion;
     }
     
      public void modificar(){
         
         personaEJB.edit(persona);
     }
     
     public void eliminar(){
         
         personaEJB.remove(persona);
         
       persona = new Persona();
        lista =  personaEJB.findAll();
     }
}
