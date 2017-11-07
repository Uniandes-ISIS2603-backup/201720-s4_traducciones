(function (ng) {
    var mod = ng.module("clienteModule");
    mod.constant("clientesContext", "api/clientes");
    mod.controller('clienteCreateCtrl', ['$scope', '$http', 'clientesContext', '$state', '$rootScope',
        function ($scope, $http, clientesContext, $state, $rootScope) {
            $rootScope.edit = false;
            $scope.createCliente = function () {
                $http.post(clientesContext, {
                    name: $scope.clienteName,
                    correo: $scope.clienteCorreo                    
                }).then(function (response) {
                    //Author created successfully
                    $state.go('clientesList', {clienteId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);


