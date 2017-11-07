(function (ng) {
    var mod = ng.module("clienteModule");
    mod.constant("clientesContext", "api/clientes");
    mod.controller('clienteUpdateCtrl', ['$scope', '$http', 'clientesContext', '$state',
        function ($scope, $http, clientesContext, $state) {
            var idCliente = $state.params.clienteId;
            $http.get(clientesContext + '/' + idCliente ).then(function (response) {
                $scope.clienteName = response.data.name;
                $scope.clienteCorreo = response.data.correo;
            });
            $scope.updateCliente = function(){
            $http.put(clientesContext + '/' + idCliente, {
                name: $scope.clienteName,
                correo: $scope.clienteCorreo
            }).then(function (response) {
                //empleado updated successfully
                $state.go('clientesList', {clienteId: response.data.id}, {reload: true});
            });
        };
    }
]);
})(angular);
