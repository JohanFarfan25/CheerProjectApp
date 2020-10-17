/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.club.controller;

import com.club.ejb.EjerciciosFacadeLocal;
import com.club.ejb.NivelFacadeLocal;
import com.club.ejb.PlanDeEntrenamientoFacadeLocal;
import com.club.ejb.UsuarioFacadeLocal;
import com.club.model.Ejercicios;
import com.club.model.Nivel;
import com.club.model.Persona;
import com.club.model.PlanDeEntrenamiento;
import com.club.model.Usuario;
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
@Named("PlanDeEntrenamientoJpaController")
@ManagedBean(name = "plan_de_entrenamiento")
@ViewScoped
public class PlanDeEntrenamientoJpaController implements Serializable {

    @EJB
    private PlanDeEntrenamientoFacadeLocal ejbFacade;
    
    @EJB
    private NivelFacadeLocal ejbNiveles;
    @EJB
    private UsuarioFacadeLocal ejbUsuarios;
    
    @EJB
    private EjerciciosFacadeLocal ejbEjercicios;
    
    
    
    private List<PlanDeEntrenamiento> items = null;
    private List<Nivel> niveles;
    private List<Usuario> usuarios;
    private List<Usuario> entrenador;  
    private List<Ejercicios> ejercicios;
    private PlanDeEntrenamiento selected;
    

    @PostConstruct
    public void inicial() {

        
        selected = new PlanDeEntrenamiento();
        
        selected.setIdNivel(new Nivel());
        selected.setIdEjercicio1(new Ejercicios());
        selected.setIdEjercicio2(new Ejercicios());
        selected.setIdEjercicio3(new Ejercicios());
        selected.setIdEjercicio4(new Ejercicios());
        
        
        
        selected.setIdDeportista(new Usuario());
        selected.getIdDeportista().setCodigo(new Persona());
        
        selected.setIdEntrenador(new Usuario());
        selected.getIdEntrenador().setCodigo(new Persona());
        
        
        items = ejbFacade.findAll();
        niveles=ejbNiveles.findAll();
        ejercicios=ejbEjercicios.findAll();
        usuarios=ejbUsuarios.listarDeportistas();
        entrenador=ejbUsuarios.listarEntrenador();
        
        
        

    }

    public String stringDeportista(){
        return selected.getIdDeportista().getCodigo().getNombre()==null?" ":(selected.getIdDeportista().getCodigo().getNombre()+ " "+selected.getIdDeportista().getCodigo().getApellidos());
    }
    
    public String stringEntrenador(){
        return selected.getIdEntrenador().getCodigo().getNombre()==null?" ":(selected.getIdEntrenador().getCodigo().getNombre()+ " "+selected.getIdEntrenador().getCodigo().getApellidos());
    }


    public PlanDeEntrenamientoFacadeLocal getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(PlanDeEntrenamientoFacadeLocal ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public List<PlanDeEntrenamiento> getItems() {
        return items;
    }

    public void setItems(List<PlanDeEntrenamiento> items) {
        this.items = items;
    }

    public PlanDeEntrenamiento getSelected() {
        return selected;
    }

    public void setSelected(PlanDeEntrenamiento selected) {
        this.selected = selected;
    }

    public List<Usuario> getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(List<Usuario> entrenador) {
        this.entrenador = entrenador;
    }
    
    public List<Nivel> getNiveles() {
        return niveles;
    }

    public void setNiveles(List<Nivel> niveles) {
        this.niveles = niveles;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public List<Ejercicios> getEjercicios() {
        return ejercicios;
    }

    public void setEjercicios(List<Ejercicios> ejercicios) {
        this.ejercicios = ejercicios;
    }
    

    public void registrar() {
        try {

            ejbFacade.create(selected);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "¡Aviso!", "Plan De Entrenamiento Creado Correctamente"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "¡Aviso!", "Error"));
        }
    }

    public void leer(PlanDeEntrenamiento Seleccion) {

        selected = Seleccion;
    }

    public void modificar() {

        try {

            ejbFacade.edit(selected);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "¡Aviso!", "Categoría Editada Correctamente"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "¡Aviso!", "Error"));
        }
    }

    public void eliminar() {

        ejbFacade.remove(selected);

        items = ejbFacade.findAll();
        selected = new PlanDeEntrenamiento();
    }

    
    
    

}
