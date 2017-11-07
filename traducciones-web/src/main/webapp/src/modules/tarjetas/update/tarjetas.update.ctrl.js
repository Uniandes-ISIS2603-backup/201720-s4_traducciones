(function (ng) {
    var mod = ng.module("tarjetaModule");
    mod.constant("tarjetasContext", "api/tarjetas");
    mod.controller('tarjetaUpdateCtrl', ['$scope', '$http', 'tarjetasContext', '$state',
        function ($scope, $http, tarjetasContext, $state) {
            var idTarjeta = $state.params.tarjetaId;
            $http.get(tarjetasContext + '/' + idTarjeta ).then(function (response) {
                $scope.tarjetaNumero = response.data.numero;
                $scope.tarjetaCodigo = response.data.codigoSeguridad;
                $scope.tarjetaCompañia = response.data.compañia;
                $scope.tarjetaNombres = response.data.nombres;
                $scope.tarjetaApellidos = response.data.apellidos;
                $scope.tarjetaFechaExpiracion = new Date(response.data.fechaExpiracion);
            });
            $scope.updateTarjeta = function(){
            $http.put(tarjetasContext + '/' + idTarjeta, {
                numero: $scope.tarjetaNumero,
                codigoSeguridad: $scope.tarjetaCodigo,
                compañia: $scope.tarjetaCompañia,
                nombres: $scope.tarjetaNombres,
                apellidos: $scope.tarjetaApellidos,
                fechaExpiracion: $scope.tarjetaFechaExpiracion
            }).then(function (response) {
                //pago updated successfully
                $state.go('tarjetasList', {tarjetaId: response.data.id}, {reload: true});
            });
        };
    }
]);
})(angular);

