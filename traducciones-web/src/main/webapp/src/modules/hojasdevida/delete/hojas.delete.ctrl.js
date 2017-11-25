(function (ng) {
    var mod = ng.module("hojaModule");
    mod.constant("hojasContext", "/hojadevida");
    mod.controller('hojasDeleteCtrl', ['$scope', '$http', 'hojasContext', '$state',
        function ($scope, $http, hojasContext, $state) {
            var idHoja = $state.params.hojaId;
            $scope.idHoja=idHoja;
            var empleadoId=$state.params.empleadoId;
            $scope.empleadoId=empleadoId;
            $scope.deleteHoja = function () {
               
                $http.delete("api/hojadevida" + '/' + idHoja, {}).then(function (response) {
                    $state.go('empleadosList', {hojaId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);