(function (ng) {
    var mod = ng.module("tarjetaModule");
    mod.constant("pagosContext", "api/tarjetas");
    mod.controller('tarjetaDeleteCtrl', ['$scope', '$http', 'tarjetasContext', '$state',
        function ($scope, $http, tarjetasContext, $state) {
            var idTarjeta = $state.params.tarjetaId;
            $scope.deleteTarjeta = function () {
                $http.delete(tarjetasContext + '/' + idTarjeta, {}).then(function (response) {
                    $state.go('tarjetasList', {tarjetaId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);


