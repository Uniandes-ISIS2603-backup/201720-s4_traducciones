(function (ng) {
    var mod = ng.module("clienteModule");
    mod.constant("clientesContext", "api/clientes");
    mod.controller('clienteCtrl', ['$scope', '$http', 'clientesContext', '$state',
        function ($scope, $http, clientesContext, $state) {
            $http.get(clientesContext).then(function (response) {
                $scope.clientesRecords = response.data;
            });

            if ($state.params.clienteId !== undefined && $state.params.clienteId !== null) {
                $http.get(clientesContext + '/' + $state.params.clienteId).then(function (response) {
                    $scope.currentCliente = response.data;
                });
            }
        }
    ]);
}
)(angular);

