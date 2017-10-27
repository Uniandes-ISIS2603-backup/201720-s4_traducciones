(function (ng) {
    var mod = ng.module("trabajoModule");
    mod.constant("trabajoContext", "api/trabajos");
    mod.controller('trabajoCtrl', ['$scope', '$http', 'trabajoContext', '$state',
        function ($scope, $http, trabajoContext, $state) {
            
            if ($state.params.trabajoId !== undefined && $state.params.trabajoId !== null) {
                $http.get(trabajoContext + '/' + $state.params.trabajoId).then(function (response) {
                    $scope.currentTrabajo = response.data;
                    $scope.idActual="ssssssssssss";
                    
                });
            }
            
            $http.get(trabajoContext).then(function (response) {
                $scope.trabajosRecords = response.data;
                $scope.idActual="nnnnnnnnnnnn";
                $scope.prueba1 = response.data[1];
                    
            });

            
        }
    ]);
}
)(angular);