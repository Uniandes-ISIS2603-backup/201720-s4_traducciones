(function (ng) {
    var mod = ng.module("pagoModule");
    mod.constant("pagosContext", "api/pagos");
    mod.controller('pagoUpdateCtrl', ['$scope', '$http', 'pagosContext', '$state',
        function ($scope, $http, pagosContext, $state) {
            var idPago = $state.params.pagoId;
            $http.get(pagosContext + '/' + idPago ).then(function (response) {
                $scope.pagoIdEmpleado = response.data.idEmpleado;
                $scope.pagoIdSolicitud = response.data.idSolicitud;
            });
            $scope.updatePago = function(){
            $http.put(pagosContext + '/' + idPago, {
                idEmpleado: $scope.pagoIdEmpleado,
                idSolicitud: $scope.pagoIdSolicitud
            }).then(function (response) {
                //pago updated successfully
                $state.go('pagosList', {pagoId: response.data.id}, {reload: true});
            });
        };
    }
]);
})(angular);

