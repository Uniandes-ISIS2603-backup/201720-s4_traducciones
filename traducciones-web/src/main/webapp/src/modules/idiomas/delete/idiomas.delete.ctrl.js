(function (ng) {
    var mod = ng.module("idiomaModule");
    mod.constant("hojasContext", "api/hojadevida");
    mod.controller('idiomasDeleteCtrl', ['$scope', '$http', 'hojasContext', '$state',
        function ($scope, $http, hojasContext, $state) {
            var idHoja = $state.params.hojaId;
            var idIdioma = $state.params.idiomaId;
            var idEmpleado = $state.params.empleadoId;
            $scope.deleteIdioma = function () {
                $http.delete(hojasContext + '/' + idHoja+ "/idiomas/"+idIdioma, {}).then(function (response) {
                    
                    $state.go('empleadoHojas', {hojaId: idHoja,empleadoId:idEmpleado}, {reload: true});
                    
                });
            };
        }
    ]);
}
)(angular);