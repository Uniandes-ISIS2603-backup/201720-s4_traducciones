(function (ng) {
    var mod = ng.module("tarjetaModule");
    mod.constant("tarjetasContext", "api/tarjetas");
    mod.controller('tarjetaCreateCtrl', ['$scope', '$http', 'tarjetasContext', '$state', '$rootScope',
        function ($scope, $http, tarjetasContext, $state, $rootScope) {
            $rootScope.edit = false;
            $scope.createTarjeta = function () {
                $http.post(tarjetasContext, {
                    numero: $scope.tarjetaNumero,
                    codigoSeguridad: $scope.tarjetaCodigo,
                    compañia: $scope.tarjetaCompañia,
                    nombres: $scope.tarjetaNombres,
                    apellidos: $scope.tarjetaApellidos,
                    fechaExpiracion: $scope.tarjetaFechaExpiracion
                }).then(function (response) {
                    //Author created successfully
                    $state.go('tarjetasList', {tarjetaId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);

