(function (ng) {
    var mod = ng.module("empleadoModule");
    mod.constant("empleadosContext", "api/empleados");
    mod.controller('hojasDetailedCtrl', ['$scope', '$http', 'empleadosContext', '$state',
        function ($scope, $http, empleadosContext, $state) {
            $scope.desdeEmpleado=true;
            var idEmpleado = $state.params.empleadoId;
            var idHojaEmpleado=$state.params.empleadoHoja;
           $scope.prueba=$state.params.empleadoHoja;
           $scope.prueba2=$state.params.empleadoId;

           

            if(idHojaEmpleado!== undefined && idHojaEmpleado !== null)
            {
            $http.get(empleadosContext + '/' + idEmpleado + "/hojadevida").then(function (response) {

                idH=response.data.id;
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