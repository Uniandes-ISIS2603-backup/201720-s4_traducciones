(function (ng) {
    var mod = ng.module("solicitudModule");
    mod.constant("solicitudesContext", "api/solicitudes");
    mod.controller('solicitudCtrl', ['$scope', '$http', 'solicitudesContext', '$state',
        function ($scope, $http, solicitudesContext, $state) {
            $http.get(solicitudesContext).then(function (response) {
                $scope.solicitudesRecords = response.data;
            });

            if (($state.params.solicitudId !== undefined) && ($state.params.solicitudId !== null)) {
                $http.get(solicitudesContext + '/' + $state.params.solicitudId).then(function (response) {
                    $scope.areasRecords = response.data.areasDeConocimiento;
                    $scope.currentSolicitud = response.data;
                });
            }
        }
    ]);
}
)(window.angular);
