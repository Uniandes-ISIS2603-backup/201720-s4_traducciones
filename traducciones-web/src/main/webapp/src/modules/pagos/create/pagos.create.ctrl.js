(function (ng) {
    var mod = ng.module("pagoModule");
    mod.constant("pagosContext", "api/pagos");
    mod.controller('pagoCreateCtrl', ['$scope', '$http', 'pagosContext', '$state', '$rootScope',
        function ($scope, $http, pagosContext, $state, $rootScope) {
            $rootScope.edit = false;
            $scope.createPago = function () {
                $http.post(pagosContext, {
                    idEmpleado: $scope.pagoIdEmpleado,
                    idSolicitud: $scope.pagoIdSolicitud                    
                }).then(function (response) {
                    //Author created successfully
                    $state.go('pagosList', {pagoId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);

