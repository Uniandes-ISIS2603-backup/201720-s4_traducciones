(function (ng) {
    var mod = ng.module("trabajoModule");
    mod.constant("trabajosContext", "api/trabajos");
    mod.controller('trabajosNewCtrl', ['$scope', '$http', 'trabajosContext', '$state', '$rootScope',
        function ($scope, $http, trabajosContext, $state, $rootScope) {
            $rootScope.edit = false;
            $scope.createTrabajo = function () {
                $http.post(trabajosContext, {
                    name: $scope.trabajoName,
                    id:$scope.trabajoId,
                    terminado: $scope.trabajoTerminado
                }).then(function (response) {
                    //Author created successfully
                    $state.go('trabajosList', {trabajoId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);