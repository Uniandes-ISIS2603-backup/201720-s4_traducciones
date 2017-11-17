(function (ng) {
    var mod = ng.module("empleadoModule");
    mod.constant("empleadoContext", "api/empleados");
    mod.controller('empleadoCtrl', ['$scope', '$http', 'empleadosContext', '$state',
        function ($scope, $http, empleadosContext, $state) {
            $http.get(empleadosContext).then(function (response) {
                $scope.empleadosRecords = response.data;
            });

            if ($state.params.empleadoId !== undefined) {
                $http.get(empleadosContext + '/' + $state.params.empleadoId).then(function (response) {
                    $scope.currentEmpleado = response.data;
                });
            }
            $scope.darTipo = function darTipo(a){
                if(a == 1){
                    return 'Traductor';
                }
                if(a == 2){
                    return "Corrector";
                }
                if(a == 3){
                    return "Ambos";
                }
            }
        }
    ]);
}
)(angular);