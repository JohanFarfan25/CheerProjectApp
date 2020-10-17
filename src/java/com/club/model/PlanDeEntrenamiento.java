/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.club.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author johan.farfan
 */
@Entity
@Table(name = "plan_de_entrenamiento", catalog = "gargolasproject", schema = "")
public class PlanDeEntrenamiento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdPlan", nullable = false)
    private Integer idPlan;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "Fecha_Creacion", insertable = false)   
    private Date fechaCreacion;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "Fecha_Inicio", nullable = false)
    private Date fechaInicio;
    
    
    @Temporal(TemporalType.DATE)
    @Column(name = "Fecha_Final", nullable = false)
    private Date fechaFinal;

    @Column(name = "Comprobante_De_Pago")
    private String comprobanteDePago;

    @Column(name = "Estado")
    private String estado;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPlan", fetch = FetchType.EAGER)
    private Collection<Semana4> semana4Collection;
    
    @JoinColumn(name = "IdEntrenador", referencedColumnName = "codigo")
    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario idEntrenador;
    
    @JoinColumn(name = "IdNivel", referencedColumnName = "IdNivel")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Nivel idNivel;
    
    @JoinColumn(name = "IdEjercicio_1", referencedColumnName = "IdEjercicios")
    @ManyToOne(fetch = FetchType.EAGER)
    private Ejercicios idEjercicio1;
    
    @JoinColumn(name = "IdEjercicio_2", referencedColumnName = "IdEjercicios")
    @ManyToOne(fetch = FetchType.EAGER)
    private Ejercicios idEjercicio2;
    
    @JoinColumn(name = "IdEjercicio_3", referencedColumnName = "IdEjercicios")
    @ManyToOne(fetch = FetchType.EAGER)
    private Ejercicios idEjercicio3;
    
    @JoinColumn(name = "IdEjercicio_4", referencedColumnName = "IdEjercicios")
    @ManyToOne(fetch = FetchType.EAGER)
    private Ejercicios idEjercicio4;
    
    @JoinColumn(name = "IdDeportista", referencedColumnName = "codigo")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Usuario idDeportista;
    

    public PlanDeEntrenamiento() {
    }

    public Integer getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(Integer idPlan) {
        this.idPlan = idPlan;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public String getComprobanteDePago() {
        return comprobanteDePago;
    }

    public void setComprobanteDePago(String comprobanteDePago) {
        this.comprobanteDePago = comprobanteDePago;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Collection<Semana4> getSemana4Collection() {
        return semana4Collection;
    }

    public void setSemana4Collection(Collection<Semana4> semana4Collection) {
        this.semana4Collection = semana4Collection;
    }

    public Usuario getIdEntrenador() {
        return idEntrenador;
    }

    public void setIdEntrenador(Usuario idEntrenador) {
        this.idEntrenador = idEntrenador;
    }

    public Nivel getIdNivel() {
        return idNivel;
    }

    public void setIdNivel(Nivel idNivel) {
        this.idNivel = idNivel;
    }

    public Ejercicios getIdEjercicio1() {
        return idEjercicio1;
    }

    public void setIdEjercicio1(Ejercicios idEjercicio1) {
        this.idEjercicio1 = idEjercicio1;
    }

    public Ejercicios getIdEjercicio2() {
        return idEjercicio2;
    }

    public void setIdEjercicio2(Ejercicios idEjercicio2) {
        this.idEjercicio2 = idEjercicio2;
    }

    public Ejercicios getIdEjercicio3() {
        return idEjercicio3;
    }

    public void setIdEjercicio3(Ejercicios idEjercicio3) {
        this.idEjercicio3 = idEjercicio3;
    }

    public Ejercicios getIdEjercicio4() {
        return idEjercicio4;
    }

    public void setIdEjercicio4(Ejercicios idEjercicio4) {
        this.idEjercicio4 = idEjercicio4;
    }

    public Usuario getIdDeportista() {
        return idDeportista;
    }

    public void setIdDeportista(Usuario idDeportista) {
        this.idDeportista = idDeportista;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + Objects.hashCode(this.idPlan);
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
        final PlanDeEntrenamiento other = (PlanDeEntrenamiento) obj;
        if (!Objects.equals(this.idPlan, other.idPlan)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PlanDeEntrenamiento{" + "idPlan=" + idPlan + '}';
    }

    
}
