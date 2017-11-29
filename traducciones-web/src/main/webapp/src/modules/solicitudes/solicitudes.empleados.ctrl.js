(function (ng) {
    var mod = ng.module("empleadoModule");
    mod.constant("empleadoContext", "api/empleados");
    mod.controller('empleadoSolicitudes', ['$scope', '$http', 'empleadosContext', '$state',
        function ($scope, $http, empleadosContext, $state) {
            var empleado;
            $http.get('api/empleados/' + $state.params.empleadoId).then(function (response) {
                empleado = response.data;
                $scope.nombreEmpleado = response.data.name;
            });
            
            $http.get('api/solicitudes').then(function (response) {
                //en este arreglo se agregaran las solicitudes que coincidan con el empleado
                var matches= [];
                //recorro las solicitudes
                for(i = 0; i<response.data.length; i++){
                    var idiomaOrigen = response.data[i].idiomaEntrada.name;
                    var idiomaDestino = response.data[i].idiomaSalida.nombre;
                    var tipo = response.data[i].Tipo;
                    var area = response.data[i].areasDeConocimiento[0].name;
                    
                    //variables que indican si el empleado tiene los idiomas , el area y el tipo de la solicitud. 0 = no, 1 = si.
                    var tieneOrigen, tieneDestino, tieneTipo, tieneArea = 0;
                    
                    //recorro los idiomas del empleado
                    for(j = 0; j<empleado.hojaDeVida.idiomas.length; j++){
                        // si el idioma origen de la solicitud lo tiene el empleado, es candidato
                        if(empleado.hojaDeVida.idiomas[j].nombre === idiomaOrigen){
                            tieneOrigen = 1;
                        }
                        // si el idioma destino de la solicitud lo tiene el empleado, es candidato
                        if(empleado.hojaDeVida.idiomas[j].nombre === idiomaDestino){
                            tieneDestino = 1;
                        }
                    }
                    //Si el empleado concuerda su tipo con el tipo de la solicitud, o si el empleado tiene como tipo "ambos", es candidato.
                    if(empleado.tipo === tipo || empleado.tipo === 3){
                        tieneTipo = 1;
                    }
                    //ahora reviso las areas de conocimiento del empleado a ver si alguna concuerda con la de la solicitud
                    for(k = 0; k<empleado.areasdeconocimiento.length; k++){
                        if(empleado.areasdeconocimiento[k].name === area){
                            tieneArea = 1;
                        }
                    }
                    //Si los idiomas y el tipo concuerdan (falta las areas), se agrega a las correspondencias.
                    //TODO recorrer y verificar las areas de conocimiento de la solicitud
                    if(tieneOrigen === 1 && tieneDestino === 1 && tieneTipo === 1 && tieneArea === 1){
                        matches.push(response.data[i]);
                    } 
                }
                
                $scope.correspondencias = matches;
            });
            
            $scope.darTipo = function darTipo(a){
                if(a === 1){
                    return 'Traductor';
                }
                if(a === 2){
                    return "Corrector";
                }
                if(a === 3){
                    return "Ambos";
                }
            }
        }
    ]);
}
        )(angular);