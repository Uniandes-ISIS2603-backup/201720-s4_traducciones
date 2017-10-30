(function (ng) {
    var mod = ng.module("trabajoModule");
    mod.constant("trabajosContext", "api/trabajos");
    mod.controller('trabajosDeleteCtrl', ['$scope', '$http', 'trabajosContext', '$state',
        function ($scope, $http, trabajosContext, $state) {
            var idTrabajo = $state.params.trabajoId;
            $scope.pruebadedelete="nnnnnnnnnnnn2";
            $scope.deleteTrabajo = function () {
                $scope.pruebadedelete="ssssssssssss";
                $http.delete(trabajosContext + '/' + idTrabajo, {}).then(function (response) {
                    $state.go('trabajosList', {trabajoId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);