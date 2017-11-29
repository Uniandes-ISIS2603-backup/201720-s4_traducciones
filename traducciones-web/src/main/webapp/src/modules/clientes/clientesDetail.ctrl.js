(function (ng) {
    var mod = ng.module("clienteModule");
    mod.constant("clientesContext", "api/clientes");
    mod.controller('clientesDetailCtrl', ['$scope', '$http', 'clientesContext', '$state',
        function ($scope, $http, clientesContext, $state) {



            $http.get(clientesContext + '/' + $state.params.clienteId).then(function (response) {
                $scope.currentCliente = response.data;
            });

        }
    ]);
}
)(angular);
