/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.traducciones.enums;


/**
 * Clase que define el estado de una propuesta.
 * @author av.perezb
 */
public enum Estado {
    
    /**
     * La propuesta aún está en revisión.
     */
    EN_REVISION,
    
    /**
     * La propuesta fue aceptada por el cliente.
     */
    ACEPTADA,
    
    /**
     * La propuesta fue rechazada por el cliente, es decir, el cliente aceptó otra propuesta.
     */
    RECHAZADA;
    
}
