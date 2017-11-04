(function (ng) {
    var mod = ng.module("areadeconocimientoModule");
    mod.constant("areaContext", "api/areasdeconocimiento");
    mod.controller('areadeconocimientoCtrl', ['$scope', '$http', 'areasContext', '$state',
        function ($scope, $http, areasContext, $state) {
            $http.get(areasContext).then(function (response) {
                $scope.areasRecords = response.data;
            });

            if ($state.params.areadeconocimientoId !== undefined) {
                $http.get(areasContext + '/' + $state.params.areadeconocimientoId).then(function (response) {
                    $scope.currentAreaDeConocimiento = response.data;
                });
            }
        }
    ]);
}
)(angular);