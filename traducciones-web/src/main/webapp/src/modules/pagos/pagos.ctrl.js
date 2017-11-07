(function (ng) {
    var mod = ng.module("pagoModule");
    mod.constant("pagosContext", "api/pagos");
    mod.controller('pagoCtrl', ['$scope', '$http', 'pagosContext', '$state',
        function ($scope, $http, pagosContext, $state) {
            $http.get(pagosContext).then(function (response) {
                $scope.pagosRecords = response.data;
            });

            if (($state.params.pagoId !== undefined) && ($state.params.pagoId !== null)) {
                $http.get(pagosContext + '/' + $state.params.pagoId).then(function (response) {
                    $scope.currentPago = response.data;
                });
            }
        }
    ]);
}
)(window.angular);


