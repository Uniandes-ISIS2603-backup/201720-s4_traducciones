(function (ng) {
    var mod = ng.module("solicitudModule");
    mod.constant("solicitudesContext", "api/solicitudes");
    mod.controller('solicitudDeleteCtrl', ['$scope', '$http', 'solicitudesContext', '$state',
        function ($scope, $http, solicitudesContext, $state) {
            var idSolicitud = $state.params.solicitudId;
            $scope.deleteSolicitud = function () {
                $http.delete(solicitudesContext + '/' + idSolicitud, {}).then(function (response) {
                    $state.go('clientesList', {solicitudId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);
