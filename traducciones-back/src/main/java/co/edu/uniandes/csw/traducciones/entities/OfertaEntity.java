/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.traducciones.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author av.perezb
 */
@Entity
public class OfertaEntity extends BaseEntity implements Serializable {

    @PodamExclude
    @OneToMany(mappedBy = "oferta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PropuestaEntity> propuestas = new ArrayList<PropuestaEntity>();

    @PodamExclude
    @ManyToOne
    private EmpleadoEntity empleado;

    /**
     * Cantidad inicial de cupones para esta oferta
     */
    private Integer cantidadInicial;

    /**
     * Cantidad actual de cupones para esta oferta
     */
    private Integer cantidadActual;

    /**
     * Descripción de la oferta
     */
    private String descripcion;

    /**
     * Código de la oferta
     */
    private String codigo;

    /**
     * Descuento que cubre la oferta
     */
    private int descuento;

    /**
     * Obtiene el atributo descuento.
     *
     * @return descuento de la oferta.
     */
    public int getDescuento() {
        return descuento;
    }

    /**
     * Establece el valor del atributo descuento.
     *
     * @param descuento nuevo valor del atributo
     */
    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaVigencia;

    /**
     * Obtiene el atributo cantidadinicial.
     *
     * @return cantidad inicial de cupones de la oferta.
     */
    public Integer getCantidadInicial() {
        return cantidadInicial;
    }

    /**
     * Establece el valor del atributo cantidadInicial.
     *
     * @param cantidadI nuevo valor del atributo
     */
    public void setCantidadInicial(Integer cantidadI) {
        this.cantidadInicial = cantidadI;
    }

    /**
     * Obtiene el atributo cantidadActual.
     *
     * @return cantidad actual de cupones disponibles de la oferta.
     */
    public Integer getCantidadActual() {
        return cantidadActual;
    }

    /**
     * Establece el valor del atributo cantidadActual.
     *
     * @param cantidadA nuevo valor del atributo
     */
    public void setCantidadActual(Integer cantidadA) {
        this.cantidadActual = cantidadA;
    }

    /**
     * Obtiene el atributo desccripción.
     *
     * @return desccripcion de la oferta.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece el valor del atributo empleado.
     *
     * @param descripcion nuevo valor del atributo
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene el atributo codigo.
     *
     * @return codigo de la oferta.
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Establece el valor del atributo codigo.
     *
     * @param cod nuevo valor del atributo
     */
    public void setCodigo(String cod) {
        this.codigo = cod;
    }

    /**
     * Obtiene el atributo fechaVigencia.
     *
     * @return fecha de vigencia de la oferta.
     */
    public Date getFechaVigencia() {
        return fechaVigencia;
    }

    /**
     * Establece el valor del atributo fechaVigencia.
     *
     * @param fechaVigencia nuevo valor del atributo
     */
    public void setFechaVigencia(Date fechaVigencia) {
        this.fechaVigencia = fechaVigencia;
    }

    /**
     * Obtiene el atributo empleado.
     *
     * @return empleado asociado a la oferta.
     */
    public EmpleadoEntity getEmpleado() {
        return empleado;
    }

    /**
     * Establece el valor del atributo empleado.
     *
     * @param empleado nuevo valor del atributo
     */
    public void setEmpleado(EmpleadoEntity empleado) {
        this.empleado = empleado;
    }

    /**
     * Obtiene la lista de propuestas asociadas a la oferta.
     *
     * @return descuento de la oferta.
     */
    public List<PropuestaEntity> getPropuestas() {
        return propuestas;
    }

    /**
     * Agrega una propuesta a la lista.
     *
     * @param propuesta nuevo valor del atributo
     */
    public void addPropuestas(PropuestaEntity propuesta) {
        this.propuestas.add(propuesta);
    }

}
