/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.club.ejb;

import com.club.model.Usuario;
import com.club.model.Usuario_;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author johan.farfan
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {

    @PersistenceContext(unitName = "primePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    @Override
    public Usuario iniciarSesion(Usuario us){
        Usuario usuario = null;
        String consulta;
        try {
            consulta="FROM Usuario u WHERE u.usuario = ?1 and u.clave = ?2";
            Query query = em.createQuery(consulta);
            query.setParameter(1, us.getUsuario());
            query.setParameter(2, us.getClave());
            
            List<Usuario>  lista = query.getResultList();
            if (!lista.isEmpty()) {
                usuario = lista.get(0);
            }
        } catch (Exception e) {
            throw e;
        }
        return usuario;
    }

    @Override
    public List<Usuario> listarDeportistas() {

        CriteriaBuilder qb = em.getCriteriaBuilder();
        CriteriaQuery<Usuario> query = qb.createQuery(Usuario.class);
        Root<Usuario> entidad = query.from(Usuario.class);
        query.select(entidad).where(qb.equal(entidad.get(Usuario_.tipo), "Deportista"));
        List<Usuario> result = em.createQuery(query).getResultList();
        return result;

    }
    
     @Override
    public List<Usuario> listarEntrenador() {

        CriteriaBuilder qb = em.getCriteriaBuilder();
        CriteriaQuery<Usuario> query = qb.createQuery(Usuario.class);
        Root<Usuario> entidad = query.from(Usuario.class);
        query.select(entidad).where(qb.equal(entidad.get(Usuario_.tipo), "Entrenador"));
        List<Usuario> result = em.createQuery(query).getResultList();
        return result;

    }
    
    
    
    
    
    
}
