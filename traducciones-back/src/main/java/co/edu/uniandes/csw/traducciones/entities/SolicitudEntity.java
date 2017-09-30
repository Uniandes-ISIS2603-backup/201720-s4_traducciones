/*
MIT License

Copyright (c) 2017 Universidad de los Andes - ISIS2603

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
package co.edu.uniandes.csw.traducciones.entities;


import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author ISIS2603
 */
@Entity
public class SolicitudEntity extends BaseEntity implements Serializable {
   private String descripcion;
   private int Tipo;

   @PodamExclude
   @OneToOne
   private ClienteEntity cliente;
   @Temporal(javax.persistence.TemporalType.DATE)
   private Date fechaInicio;
   @Temporal(javax.persistence.TemporalType.DATE)
   private Date fechaEntrega;
   private int numPalabras;
   @PodamExclude
   @OneToMany
   private List<AreaDeConocimientoEntity> areasDeConocimiento;
   
    @PodamExclude
    @OneToOne
    private IdiomaEntity idiomaEntrada;
    
    @PodamExclude
    @OneToOne
    private PropuestaEntity propuestaElejida;
    
    @PodamExclude
    @OneToMany
    private List<PropuestaEntity> propuestas;
    public List<AreaDeConocimientoEntity> getAreasDeConocimiento() {
        return areasDeConocimiento;
    }

    public void setAreasDeConocimiento(List<AreaDeConocimientoEntity> areasDeConocimiento) {
        this.areasDeConocimiento = areasDeConocimiento;
    }

    public IdiomaEntity getIdiomaEntrada() {
        return idiomaEntrada;
    }

    public void setIdiomaEntrada(IdiomaEntity idiomaEntrada) {
        this.idiomaEntrada = idiomaEntrada;
    }

    public IdiomaEntity getIdiomaSalida() {
        return idiomaSalida;
    }

    public void setIdiomaSalida(IdiomaEntity idiomaSalida) {
        this.idiomaSalida = idiomaSalida;
    }
    @OneToOne
    private IdiomaEntity idiomaSalida;
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public int getNumPalabras() {
        return numPalabras;
    }

    public void setNumPalabras(int numPalabras) {
        this.numPalabras = numPalabras;
    }
     public int getTipo() {
        return Tipo;
    }

    public void setTipo(int Tipo) {
        this.Tipo = Tipo;
    }

    public PropuestaEntity getPropuestaElejida() {
        return propuestaElejida;
    }

    public void setPropuestaElejida(PropuestaEntity propuestaElejida) {
        this.propuestaElejida = propuestaElejida;
    }

    public List<PropuestaEntity> getPropuestas() {
        return propuestas;
    }

    public void setPropuestas(List<PropuestaEntity> propuestas) {
        this.propuestas = propuestas;
    }
    public ClienteEntity getCliente() {
        return cliente;
    }

    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }
}