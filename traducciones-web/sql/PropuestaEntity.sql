/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  av.perezb
 * Created: 29/09/2017
 */

delete from PropuestaEntity;
insert into PropuestaEntity (id, name, costo, oferta_id, estado) values (1, 'nombre1', 20.3, 'ABC2345', null,  '30% de descuento', 'ACEPTADA' );
insert into PropuestaEntity (id, name, costo, oferta_id, estado) values (2, 'nombre2', 21.6, 'ABC2645', null,  '40% de descuento', 'ACEPTADA');
insert into PropuestaEntity (id, name, costo, oferta_id, estado) values (3, 'nombre3', 22.9, 'ABC2445', null,  '50% de descuento', 'EN_REVISION');
insert into PropuestaEntity (id, name, costo, oferta_id, estado) values (4, 'nombre4', 23.4, 'ABD2345', null,  '60% de descuento', 'RECHAZADA');
insert into PropuestaEntity (id, name, costo, oferta_id, estado) values (5, 'nombre5', 24.3, 'ABC2145', null, '70% de descuento', 'RECHAZADA');


update PropuestaEntity set Oferta_id = 1 where id = 1;
update PropuestaEntity set Oferta_id = 2 where id = 2;
update PropuestaEntity set Oferta_id = 3 where id = 3;