(function (ng) {
    var mod = ng.module("areadeconocimientoModule");
    mod.constant("areasdeconocimientoContext", "api/areasdeconocimiento");
    mod.controller('areadeconocimientoDeleteCtrl', ['$scope', '$http', 'areasdeconocimientoContext', '$state',
        function ($scope, $http, areasdeconocimientoContext, $state) {
            var idAreadeconocimiento = $state.params.areadeconocimientoId;
            $scope.deleteAreadeconocimiento = function () {
                $http.delete(areasdeconocimientoContext + '/' + idAreadeconocimiento, {}).then(function (response) {
                    $state.go('areasList', {areadeconocimientoId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);