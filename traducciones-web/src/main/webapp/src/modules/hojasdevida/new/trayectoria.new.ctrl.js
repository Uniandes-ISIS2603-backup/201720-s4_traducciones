(function (ng) {
    var mod = ng.module("hojaModule");
    mod.controller('trayectoriasNewCtrl', ['$scope', '$http', '$state', '$rootScope',
        function ($scope, $http, $state) {
            
            var idHoja=$state.params.hojaId;
            var idEmpleado=$state.params.empleadoId;
            $scope.idEmp=idEmpleado;
            $scope.idHoj=idHoja;
            
            

            $scope.addTrayectoria = function () {
                $http.post("api/hojadevida/" + idHoja + "/trayectorias", {
                    descripcion: $scope.trayectoriaDescripcion,
                    fechaFin: $scope.trayectoriaFechaF,
                    fechaInicio: $scope.trayectoriaFechaI,
                    name: $scope.trayectoriaName

                }).then(function () {
                    //Author created successfully
                    $state.go('empleadoHojas', {empleadoId:idEmpleado,empleadoHoja: idHoja}, {reload: true});
                });
            };


        }
    ]);
}
)(angular);