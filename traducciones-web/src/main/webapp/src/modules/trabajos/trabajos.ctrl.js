(function (ng) {
    var mod = ng.module("trabajoModule");
    mod.constant("trabajoContext", "api/trabajos");
    mod.controller('trabajoCtrl', ['$scope', '$http', 'trabajoContext', '$state',
        function ($scope, $http, trabajoContext, $state) {
            if ($state.params.trabajoId !== undefined && $state.params.trabajoId !== null) {
                $http.get(trabajoContext + '/' + $state.params.trabajoId).then(function (response) {
                    $scope.currentTrabajo = response.data;
                    var bool=response.data.terminado;
                });
            }
            $http.get(trabajoContext).then(function (response) {
                $scope.trabajosRecords = response.data;
                $scope.terminadoBool = function (bool) {
                        if (bool) {
                            return "Terminado";
                        } else {
                            return "No Terminado";
                        }
                    };
            });
        }
    ]);
}
)(angular);