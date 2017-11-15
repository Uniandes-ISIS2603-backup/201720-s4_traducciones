(function (ng) {
    var mod = ng.module("empleadoModule");
    mod.constant("empleadosContext", "api/empleados");
    mod.controller('hojasDetailedCtrl', ['$scope', '$http', 'empleadosContext', '$state','$rootScope',
        function ($scope, $http, empleadosContext, $state,$rootScope) {
            $rootScope.desdeEmpleado=true;
            var idEmpleado = $state.params.empleadoId;
            var idHojaEmpleado=$state.params.empleadoHoja;
           $scope.prueba=$state.params.empleadoHoja;
           $scope.prueba2=$state.params.empleadoId;

            
            $scope.formatoFecha = function(f)
            {
                var meses = new Array("Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre");
                var diasSemana = new Array("Domingo", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado");
                var fff = new Date(f);
               var txt=diasSemana[fff.getDay()] + ", " + fff.getDate() + " de " + meses[fff.getMonth()] + " de " + fff.getFullYear();
               return txt;
            };

            if(idHojaEmpleado!== undefined && idHojaEmpleado !== null)
            {
            $http.get(empleadosContext + '/' + idEmpleado + "/hojadevida").then(function (response) {

                var idH=response.data.id;
                $scope.idEmpleado=idEmpleado;
                $scope.idHojaDeVida = response.data.id;
                $scope.nombreHojaDeVida = response.data.name;
                $scope.descripcionHojaDeVida = response.data.descripcion;
                $scope.formacionHojaDeVida = response.data.formacionAcademica;
                $scope.perfilHojaDeVida = response.data.perfilProfesional;
                $http.get("api/hojadevida/"+idH).then(function (response2)
                {                  
                $scope.trayectoriasHoja=response2.data.trayectorias;
                $scope.idiomasHoja=response2.data.idiomas;
                });
                

            });
        }
        }
    ]);
}
)(angular);