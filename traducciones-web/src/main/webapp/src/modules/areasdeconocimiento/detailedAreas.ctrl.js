(function (ng) {
    var mod = ng.module("empleadoModule");
    mod.constant("empleadosContext", "api/empleados");
    mod.controller('areasDetailedCtrl', ['$scope', '$http', 'empleadosContext', '$state',
        function ($scope, $http, empleadosContext, $state) {
            var idEmpleado = $state.params.empleadoId;
            //obtener nombre del empleado
            $http.get(empleadosContext + '/' + idEmpleado).then(function(response){
                $scope.nombreEmpleado = response.data.name;
                $scope.idEmpleado = response.data.id;
            });
            
            $http.get(empleadosContext + '/' + idEmpleado + "/areasdeconocimiento").then(function (response) {
                $scope.areasDetailed = response.data;
            });
        }
    ]);
}
)(angular);