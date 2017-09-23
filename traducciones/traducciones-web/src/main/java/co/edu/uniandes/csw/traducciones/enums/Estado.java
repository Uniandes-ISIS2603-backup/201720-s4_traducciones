/*/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package co.edu.uniandes.csw.traducciones.enums;

/**
 *
 * @author av.perezb
 */
public enum Estado {
    
    /**
     * La propuesta del empleado aún no ha sido aceptada/rechazada por el cliente.
     */
    EN_REVISION,
    
    /**
     * La propuesta del empleado ha sido aceptada por el cliente.
     */
    ACEPTADA,
    
    
    /**
     * El cliente aceptó una propuesta de trabajo diferente.
     */
    RECHAZADA;
    
}