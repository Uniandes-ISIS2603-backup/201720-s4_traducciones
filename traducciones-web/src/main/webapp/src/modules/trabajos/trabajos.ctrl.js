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

            if ($state.params.trabajoId !== undefined && $state.params.trabajoId !== null) {
                $http.get(trabajoContext + '/' + $state.params.trabajoId).then(function (response) {
                    $scope.currentTrabajo = response.data;

                    $scope.verificarDetail = function (param)
                {
                    if(param===-1)
                    {
                        return "Aun no calificado";
                    }
                }
                });
            }
            $http.get(trabajoContext).then(function (response) {
                $scope.trabajosRecords = response.data;
                $scope.verificar = function (param)
                {
                    if(param===-1)
                    {
                        return "No";
                    }
                    else
                    {
                        return "Si";
                    }
                }


                
            });
        }
    ]);
}
)(angular);