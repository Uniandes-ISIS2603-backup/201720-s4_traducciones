package co.edu.uniandes.csw.traducciones.dtos;

import co.edu.uniandes.csw.traducciones.entities.OfertaEntity;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author av.perezb
 */
public class OfertaDTO {

    /**
     * Id de la oferta
     */
    private Long id;

    /**
     * Nombre de la oferta
     */
    private String nombre;

    /**
     * Descripción de la oferta
     */
    private String descripcion;

    /**
     * Código de la oferta
     */
    private String codigo;

    /**
     * Fecha de vigencia de la oferta
     */
    @Temporal(TemporalType.DATE)    
    private Date fechaVigencia; 

    /**
     * Cantidad inicial de cupones de esta oferta
     */
    private Integer cantidadInicial;

    /**
     * Cantidad actual de cupones restantes de esta oferta
     */
    private Integer cantidadActual;

    /**
     * Descuento que aplica esta oferta
     */
    private int descuento;

    /**
     * @return el descuento de la oferta
     */
    public int getDescuento() {
        return descuento;
    }

    /**
     * @param descuento de la oferta
     */
    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

    /**
     * @return la cantidad inicial de la oferta
     */
    public Integer getCantidadInicial() {
        return cantidadInicial;
    }

    /**
     * @param cantidadInicial de cupones disponibles para la oferta
     */
    public void setCantidadInicial(Integer cantidadInicial) {
        this.cantidadInicial = cantidadInicial;
    }

    /**
     * @return la cantidad actual de la oferta
     */
    public Integer getCantidadActual() {
        return cantidadActual;
    }

    /**
     * @param cantidadActual de cupones disponibles para la oferta
     */
    public void setCantidadActual(Integer cantidadActual) {
        this.cantidadActual = cantidadActual;
    }

    /**
     * Constructor por defecto
     */
    public OfertaDTO() {

    }

    /**
     * Convertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param oferta: Es la entidad que se va a convertir a DTO
     */
    public OfertaDTO(OfertaEntity oferta) {

        this.id = oferta.getId();
        this.nombre = oferta.getName();
        this.descripcion = oferta.getDescripcion();
        this.codigo = oferta.getCodigo();
        this.fechaVigencia = oferta.getFechaVigencia();
        this.cantidadActual = oferta.getCantidadActual();
        this.cantidadInicial = oferta.getCantidadInicial();
        this.descuento = oferta.getDescuento();
    }

    /**
     * @return el id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id el id para modificar
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return el nombre de la oferta
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre el nombre para modificar
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return la descripción
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion la descripción para modificar
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return el código
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo el código para modificar
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return la fecha de vigencia
     */
    public Date getFechaVigencia() {
        return fechaVigencia;
    }

    /**
     * @param fechaVigencia para modificar
     */
    public void setFechaVigencia(Date fechaVigencia) {
        this.fechaVigencia = fechaVigencia;
    }

    /**
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    public OfertaEntity toEntity() {

        OfertaEntity entity = new OfertaEntity();
        entity.setId(this.id);
        entity.setName(this.nombre);
        entity.setDescripcion(this.descripcion);
        entity.setCodigo(this.codigo);
        entity.setFechaVigencia(this.fechaVigencia);
        entity.setName(this.nombre);
        entity.setCantidadActual(cantidadActual);
        entity.setCantidadInicial(cantidadInicial);
        entity.setDescuento(this.descuento);

        return entity;
    }

}
