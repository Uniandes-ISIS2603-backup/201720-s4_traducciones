(function (ng) {
    var mod = ng.module("trabajoModule");
    mod.constant("trabajoContext", "api/trabajos");
    mod.controller('trabajoCtrl', ['$scope', '$http', 'trabajoContext', '$state',
        function ($scope, $http, trabajoContext, $state) {

            $scope.terminadoBool = function (bool) {
                if (bool) {
                    return "Terminado";
                } else if (!bool)
                {
                    return "No Terminado";
                } else
                {
                    return "Invalido"
                }
            };

            $scope.verificar = function (param)
            {
                return (param === 0) ? "No" : "Si";
            };
            if ($state.params.trabajoId !== undefined && $state.params.trabajoId !== null) {
                $http.get(trabajoContext + '/' + $state.params.trabajoId).then(function (response) {
                    $scope.currentTrabajo = response.data;


                });
            }
            $http.get(trabajoContext).then(function (response) {
                $scope.trabajosRecords = response.data;




            });
        }
    ]);
}
)(angular);