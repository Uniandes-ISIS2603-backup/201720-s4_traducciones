(function (ng) {
    var mod = ng.module("tarjetaModule");
    mod.constant("tarjetasContext", "api/tarjetas");
    mod.controller('tarjetaCtrl', ['$scope', '$http', 'tarjetasContext', '$state',
        function ($scope, $http, tarjetasContext, $state) {
            $http.get(tarjetasContext).then(function (response) {
                $scope.tarjetasRecords = response.data;
            });

            if (($state.params.tarjetaId !== undefined) && ($state.params.tarjetaId !== null)) {
                $http.get(tarjetasContext + '/' + $state.params.tarjetaId).then(function (response) {
                    $scope.currentTarjeta = response.data;
                });
            }
        }
    ]);
}
)(window.angular);


