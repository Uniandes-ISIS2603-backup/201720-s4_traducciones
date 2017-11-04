(function (ng) {
    var mod = ng.module("empleadoModule");
    mod.constant("empleadosContext", "api/empleados");
    mod.controller('empleadoDeleteCtrl', ['$scope', '$http', 'empleadosContext', '$state',
        function ($scope, $http, empleadosContext, $state) {
            var idEmpleado = $state.params.empleadoId;
            $scope.deleteEmpleado = function () {
                $http.delete(empleadosContext + '/' + idEmpleado, {}).then(function (response) {
                    $state.go('empleadosList', {empleadoId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);