(function (ng) {
    var mod = ng.module("empleadoModule");
    mod.constant("empleadosContext", "api/empleados");
    mod.controller('empleadoCreateCtrl', ['$scope', '$http', 'empleadosContext', '$state',
        function ($scope, $http, empleadosContext, $state) {
            $scope.createEmpleado = function () {
                $http.post(empleadosContext, {
                    name: $scope.empleadoName,
                    tipo: $scope.empleadoTipo,
                    calificacionPromedio: 0,
                }).then(function (response) {
                    //empleado created successfully
                    $state.go('empleadosList', {empleadoId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);