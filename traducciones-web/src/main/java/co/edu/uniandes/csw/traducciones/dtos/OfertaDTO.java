package co.edu.uniandes.csw.traducciones.dtos;

import co.edu.uniandes.csw.traducciones.entities.OfertaEntity;
 import java.util.Date;
 
 /**
  *
 * @author av.perezb
  */
 public class OfertaDTO {
     
     private Long id;
     private String descripcion;
     private String codigo;
     private Date fechaVigencia;
     private String nombre;
    private Integer cantidadInicial;
    private int descuento;

    public int getDescuento() {
        return descuento;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

    public Integer getCantidadInicial() {
        return cantidadInicial;
    }

    public void setCantidadInicial(Integer cantidadInicial) {
        this.cantidadInicial = cantidadInicial;
    }

    public Integer getCantidadActual() {
        return cantidadActual;
    }

    public void setCantidadActual(Integer cantidadActual) {
        this.cantidadActual = cantidadActual;
    }
    private Integer cantidadActual;
     
      /**
      * Constructor por defecto
      */
     public OfertaDTO() {
     
     }
     
     /**
      * Convertir Entity a DTO
      * (Crea un nuevo DTO con los valores que recibe en la entidad que viene de argumento.
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

