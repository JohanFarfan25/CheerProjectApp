
package com.club.controller;
import com.club.ejb.UsuarioFacadeLocal;
import java.io.Serializable;
import com.club.model.Persona;
import com.club.model.Usuario;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class UsuarioJpaController implements Serializable{
    
    @EJB
    private UsuarioFacadeLocal usuarioEJB;
    private Usuario usuario;
    private Persona persona;
    private List<Usuario> lista;
    
    @PostConstruct
    public void init(){
        
    
        usuario = new Usuario();
        persona = new Persona();
        lista = usuarioEJB.findAll();
    }

    public UsuarioFacadeLocal getUsuarioEJB() {
        return usuarioEJB;
    }

    public void setUsuarioEJB(UsuarioFacadeLocal usuarioEJB) {
        this.usuarioEJB = usuarioEJB;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public List<Usuario> getLista() {
        return lista;
    }

    public void setLista(List<Usuario> lista) {
        this.lista = lista;
    }

    
    
   
    public void registrar(){
        try {
     
            this.usuario.setCodigo(persona);
            usuarioEJB.create(usuario);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "¡Aviso!","Usuario Creado Correctamente"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "¡Aviso!","Error"));
        }
    }
    
     public String iniciarSesion(){
         Usuario us;
         String redireccion  = null; 
        try { 
            us = usuarioEJB.iniciarSesion(usuario);
        
            if(us != null){
            
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", us);
            redireccion = "/MiPerfil/index.xhtml?faces-redirect=true";
        
           } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "¡Aviso!","Credenciales Incorrectas intente nuevamente"));
        }
               
        } catch (Exception e) {
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "¡Aviso!","Credenciales Incorrectas intente nuevamente"));
        } 
        
        return redireccion;
    }
     
     public void leer(Usuario Seleccion){
         
         usuario = Seleccion;
     }
     
      public void modificar(){
         
         usuarioEJB.edit(usuario);
     }
     
     public void eliminar(){
         
         usuarioEJB.remove(usuario);
         
        usuario = new Usuario();
        persona = new Persona();
        lista = usuarioEJB.findAll();
     }
}
