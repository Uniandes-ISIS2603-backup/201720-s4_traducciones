(function (ng) {
    var mod = ng.module("solicitudModule");
    mod.constant("solicitudesContext", "api/solicitudes");
    mod.controller('solicitudCreateCtrl', ['$scope', '$http', 'solicitudesContext', '$state', 'areaContext', '$rootScope',
        function ($scope, $http, solicitudesContext, $state, areaContext, $rootScope) {
            $rootScope.edit = false;
            $scope.createAuthor = function () {
                $http.post(solicitudesContext, {
                    descripcion: $scope.solicitudDescripcion,
                    tipo: $scope.solicitudTipo,
                    fechaInicio: $scope.solicitudFechaInicio,
                    fechaEntrega: $scope.solicitudFechaEntrega,
                    numPalabras: $scope.solicitudNumPalabras,
                }).then(function (response) {
                    //Author created successfully
                    $state.go('clientesList', {solicitudId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);