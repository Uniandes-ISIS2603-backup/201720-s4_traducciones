/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.traducciones.ejb;

import co.edu.uniandes.csw.traducciones.entities.ClienteEntity;
import co.edu.uniandes.csw.traducciones.entities.PagoEntity;
import co.edu.uniandes.csw.traducciones.entities.TarjetaDeCreditoEntity;
import co.edu.uniandes.csw.traducciones.persistence.ClientePersistence;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
/**
 *
 * @author ne.ortega
 */
@Stateless
public class ClienteLogic {
    private static final Logger LOGGER = Logger.getLogger(ClienteLogic.class.getName());

    @Inject
    private ClientePersistence persistence;

    @Inject
    private PagoLogic pagoLogic;
    
    @Inject
    private TarjetaDeCreditoLogic tarjetaLogic;

    /**
     * Obtiene la lista de los registros de Cliente.
     *
     * @return Colección de objetos de ClienteEntity.
     * @generated
     */
    public List<ClienteEntity> getClientes() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los autores");
        return persistence.findAll();
    }

    /**
     * Obtiene los datos de una instancia de Cliente a partir de su ID.
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de ClienteEntity con los datos del Author consultado.
     * @generated
     */
    public ClienteEntity getCliente(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar un cliente con id = {0}", id);
        return persistence.find(id);
    }

    /**
     * Se encarga de crear un Cliente en la base de datos.
     *
     * @param entity Objeto de ClienteEntity con los datos nuevos
     * @return Objeto de ClienteEntity con los datos nuevos y su ID.
     * @generated
     */
    public ClienteEntity createCliente(ClienteEntity entity) {
        LOGGER.log(Level.INFO, "Inicia proceso de crear un cliente ");
        persistence.create(entity);
        return entity;
    }

    /**
     * Actualiza la información de una instancia de Cliente
     *
     * @param entity Instancia de ClienteEntity con los nuevos datos.
     * @return Instancia de ClienteEntity con los datos actualizados.
     * @generated
     */
    public ClienteEntity updateCliente(ClienteEntity entity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar un cliente ");
        return persistence.update(entity);
    }

    /**
     * Elimina una instancia de Cliente de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     * @generated
     */
    public void deleteCliente(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar un cliente ");
        persistence.delete(id);
    }

    /**
     * Obtiene una colección de instancias de PagoEntity asociadas a una
     * instancia de Cliente
     *
     * @param clienteId Identificador de la instancia de Cliente
     * @return Colección de instancias de PagoEntity asociadas a la instancia de
     * Cliente
     * @generated
     */
    public List<PagoEntity> listPagos(Long clienteId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los pagos del cliente con id = {0}", clienteId);
        return getCliente(clienteId).getPagos();
    }

    /**
     * Obtiene una instancia de PagoEntity asociada a una instancia de Cliente
     *
     * @param clienteId Identificador de la instancia de Cliente
     * @param pagoId Identificador de la instancia de Pago
     * @return
     * @generated
     */
    public PagoEntity getPago(Long clienteId, Long pagoId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar un pago con id = {0}", pagoId);
        List<PagoEntity> list = getCliente(clienteId).getPagos();
        PagoEntity pagoEntity = new PagoEntity();
        pagoEntity.setId(pagoId);
        int index = list.indexOf(pagoEntity);
        if (index >= 0) {
            return list.get(index);
        }
        return null;
    }

    /**
     * Asocia un Pago existente a un Cliente
     *
     * @param clienteId Identificador de la instancia de Cliente
     * @param pagoId Identificador de la instancia de Pago
     * @return Instancia de PagoEntity que fue asociada a Cliente
     * @generated
     */
    public PagoEntity addPago(Long clienteId, Long pagoId) {
        LOGGER.log(Level.INFO, "Inicia proceso de agregar un pago al cliente con id = {0}", clienteId);
        PagoEntity pago = pagoLogic.getPago(pagoId);
        if(pago == null)
            LOGGER.log(Level.SEVERE, "El pago con el id {0} no existe", pagoId);
        
        ClienteEntity cliente = getCliente(clienteId);
        if(cliente == null)
            LOGGER.log(Level.SEVERE, "El cliente con el id {0} no existe", clienteId);
        
        List<PagoEntity> pagosCliente = cliente.getPagos();
        if(pagosCliente == null){
            pagosCliente = new ArrayList<>();
        }
        pagosCliente.add(pago);
        LOGGER.log(Level.INFO, "Termina proceso de agregar un pago al cliente con id = {0}", clienteId);
        return pago;
    }

    /**
     * Desasocia un Pago existente de un Cliente existente
     *
     * @param clienteId Identificador de la instancia de Cliente
     * @param pagoId Identificador de la instancia de Pago
     * @generated
     */
    public void removePago(Long clienteId, Long pagoId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar un pago del cliente con id = {0}", clienteId);
        if(getCliente(clienteId)==null){
            LOGGER.log(Level.SEVERE, "El cliente con el id {0} no existe", clienteId);
        }
        List<PagoEntity> pagos = getCliente(clienteId).getPagos();
        if(pagos == null){
            return;
        }
        pagos.remove(pagoLogic.getPago(pagoId));
        LOGGER.log(Level.INFO, "Termina el proceso de borrar un pago del cliente con id = {0}", clienteId);
    }
    
    /**
     * Obtiene una colección de instancias de TarjetaDeCreditoEntity asociadas a una
     * instancia de Cliente
     *
     * @param clienteId Identificador de la instancia de Cliente
     * @return Colección de instancias de TarjetaDeCreditoEntity asociadas a la instancia de
     * Cliente
     * @generated
     */
    public List<TarjetaDeCreditoEntity> listTarjetas(Long clienteId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos las tarjetas del cliente con id = {0}", clienteId);
        if(getCliente(clienteId) == null){
            LOGGER.log(Level.SEVERE, "El cliente con el id {0} no existe", clienteId);
        }
        return getCliente(clienteId).getTarjetas();
    }

    /**
     * Obtiene una instancia de TarjetaDeCreditoEntity asociada a una instancia de Cliente
     *
     * @param clienteId Identificador de la instancia de Cliente
     * @param tarjetaId Identificador de la instancia de Tarjeta
     * @return
     * @generated
     */
    public TarjetaDeCreditoEntity getTarjeta(Long clienteId, Long tarjetaId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar una tarjeta con id = {0}", tarjetaId);
        if(getCliente(clienteId)== null){
            LOGGER.log(Level.SEVERE, "El cliente con el id {0} no existe", clienteId);
            return null;
        }
        if(tarjetaLogic.getTarjeta(tarjetaId) == null){
            LOGGER.log(Level.SEVERE, "La tarjeta con el id {0} no existe", tarjetaId);
            return null;
        }
        ClienteEntity cliente = getCliente(clienteId);
        List<TarjetaDeCreditoEntity> list = cliente.getTarjetas();
        if(list == null){
            LOGGER.log(Level.SEVERE, "BusinessLogicException: El cliente no tiene tarjetas de crédito");
            return null;
        }
        TarjetaDeCreditoEntity tarjetaCliente = new TarjetaDeCreditoEntity();
        for(TarjetaDeCreditoEntity tarjeta : list){
            if(tarjeta.getId().equals(tarjetaId)){
                tarjetaCliente = tarjeta;
                break;
            }
        }
        return tarjetaCliente;
    }

    /**
     * Asocia una Tarjeta existente a un Cliente
     *
     * @param clienteId Identificador de la instancia de Cliente
     * @param tarjetaId Identificador de la instancia de Tarjeta
     * @return Instancia de TarjetaDeCreditoEntity que fue asociada a Cliente
     * @generated
     */
    public TarjetaDeCreditoEntity addTarjeta(Long clienteId, Long tarjetaId) {
        LOGGER.log(Level.INFO, "Inicia proceso de agregar una tarjeta al cliente con id = {0}", clienteId);
        TarjetaDeCreditoEntity tarjeta = tarjetaLogic.getTarjeta(tarjetaId);
        if(tarjeta == null)
            LOGGER.log(Level.SEVERE, "La tarjeta con el id {0} no existe", tarjetaId);
        
        ClienteEntity cliente = getCliente(clienteId);
        if(cliente == null)
        {
            LOGGER.log(Level.SEVERE, "El cliente con el id {0} no existe", clienteId);
            return null;
        }
        List<TarjetaDeCreditoEntity> tarjetasCliente = cliente.getTarjetas();
        if(tarjetasCliente == null){
            tarjetasCliente = new ArrayList<>();
        }
        tarjetasCliente.add(tarjeta);
        LOGGER.log(Level.INFO, "Termina proceso de agregar una tarjeta al cliente con id = {0}", clienteId);
        return tarjeta;
    }

    /**
     * Desasocia una Tarjeta existente de un Cliente existente
     *
     * @param clienteId Identificador de la instancia de Cliente
     * @param tarjetaId Identificador de la instancia de Tarjeta
     * @generated
     */
    public void removeTarjeta(Long clienteId, Long tarjetaId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar una tarjeta del cliente con id = {0}", clienteId);
        if(getCliente(clienteId) != null){
            if(tarjetaLogic.getTarjeta(tarjetaId) != null){
                List<TarjetaDeCreditoEntity> tarjetas = getCliente(clienteId).getTarjetas();
                if(tarjetas != null){
                    tarjetas.remove(tarjetaLogic.getTarjeta(tarjetaId));
                }                
            }            
        }
        LOGGER.log(Level.INFO, "Termina el proceso de borrar una tarjeta del cliente con id = {0}", clienteId);
    }
}
