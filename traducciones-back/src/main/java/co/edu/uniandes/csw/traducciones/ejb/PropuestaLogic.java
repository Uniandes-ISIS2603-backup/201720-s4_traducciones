/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.traducciones.ejb;

import co.edu.uniandes.csw.traducciones.entities.OfertaEntity;
import co.edu.uniandes.csw.traducciones.entities.PropuestaEntity;
import co.edu.uniandes.csw.traducciones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.traducciones.persistence.OfertaPersistence;
import co.edu.uniandes.csw.traducciones.persistence.PropuestaPersistence;
import java.util.List;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ejb.Stateless;

/**
 *
 * @author av.perezb
 */
@Stateless
public class PropuestaLogic {

    private static final Logger LOGGER = Logger.getLogger(PropuestaLogic.class.getName());


    @Inject
    private PropuestaPersistence persistencePropuesta; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    @Inject
    private OfertaPersistence persistenceOferta; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    /**
     * Agrega una oferta a la propuesta seleccionada.
     *
     * @param idPropuesta propuesta a la que se agregará una oferta.
     * @param oferta Oferta a agregar.
     * @return
     * @throws BusinessLogicException en caso de que no esté disponible la
     * oferta. O en caso de que la propuesta ya tenga una oferta agregada.
     *
     */
    public PropuestaEntity agregarOferta(Long idPropuesta, OfertaEntity oferta) throws BusinessLogicException {

        if (oferta.getCantidadActual() < 0) {
            throw new BusinessLogicException("La oferta ya no está disponible");
        }
        PropuestaEntity propuesta = getPropuesta(idPropuesta);

        if (propuesta.getOferta() != null) {
            throw new BusinessLogicException("La propuesta" + idPropuesta + "ya tiene una oferta agregada.");
        }

        oferta.setCantidadActual(oferta.getCantidadActual() - 1);
        oferta.getPropuestas().add(propuesta);
        propuesta.setCosto(propuesta.getCosto() - oferta.getDescuento() * propuesta.getCosto() / 100);

        persistencePropuesta.update(propuesta);
        persistenceOferta.update(oferta);

        return propuesta;
    }

    /**
     * Crea una propuesta a partir de una entidad
     *
     * @param entity Propuesta a crear
     * @return la propuesta ya creada
     */
    public PropuestaEntity createPropuesta(PropuestaEntity entity) {

        LOGGER.info("Inicia el proceso de creación de una Propuesta");
        // Invoca la persistencia para crear la Propuesta
        persistencePropuesta.create(entity);
        LOGGER.info("Termina proceso de creación de una Propuesta");
        return entity;

    }

    /**
     *
     * Obtener todas las Propuestas existentes en la base de datos.
     *
     * @return una lista de Propuestas.
     * @throws
     * co.edu.uniandes.csw.traducciones.exceptions.BusinessLogicException En
     * caso de no encontrar ninguna propuesta.
     */
    public List<PropuestaEntity> getPropuestas() throws BusinessLogicException {

        LOGGER.info("Inicia proceso de consultar todas las Propuestas");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.

        List<PropuestaEntity> listaPropuestas = persistencePropuesta.findAll();
        LOGGER.info("Termina proceso de consultar todas las Propuestas");

        return listaPropuestas;

    }

    /**
     * Obtiene los datos de una Propuesta a partir de su ID.
     *
     * @param id Identificador de la Propuesta a consultar.
     * @return una PropuestaEntity con los datos de la Propuesta consultada.
     * @throws
     * co.edu.uniandes.csw.traducciones.exceptions.BusinessLogicException En
     * caso de no encontrar ninguna propuesta.
     */
    public PropuestaEntity getPropuesta(Long id) throws BusinessLogicException {

        LOGGER.info("Inicia proceso de consultar una Propuesta según el id recibido por parámetro");
        PropuestaEntity Propuesta = persistencePropuesta.find(id);

        if (Propuesta == null) {
            throw new BusinessLogicException("No existen Propuestas con ese id.");
        }
        LOGGER.info("Termina proceso de consultar una Propuesta");

        return Propuesta;

    }

    /**
     * Actualiza la información de una Propuesta.
     *
     * @param entity Propuesta con los nuevos datos.
     * @return Instancia de PropuestaEntity con los datos actualizados.
     * @throws BusinessLogicException si la Propuesta ya fue utilizada.
     *
     */
    public PropuestaEntity updatePropuesta(PropuestaEntity entity) throws BusinessLogicException {

        LOGGER.info("Inicia la modificación de una Propuesta");

        LOGGER.info("Finaliza la modificación");
        return persistencePropuesta.update(entity);

    }

    /**
     * Elimina la propuesta ingresada por parámetro.
     *
     * @param id de la propuesta a eliminar.
     * @throws BusinessLogicException en caso de no encontrar la propuesta por
     * id.
     */
    public void deletePropuesta(Long id) throws BusinessLogicException {

        if (persistencePropuesta.find(id) == null) {

            throw new BusinessLogicException("No se encontró ninguna Propuesta con ese id.");
        }
        persistencePropuesta.delete(id);

    }


}
