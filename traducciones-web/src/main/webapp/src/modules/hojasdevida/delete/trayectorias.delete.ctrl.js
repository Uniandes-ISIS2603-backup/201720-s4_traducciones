(function (ng) {
    var mod = ng.module("hojaModule");
    mod.constant("hojasContext", "api/hojadevida");
    mod.controller('trayectoriasDeleteCtrl', ['$scope', '$http', 'hojasContext', '$state',
        function ($scope, $http, hojasContext, $state) {
            var idHoja = $state.params.hojaId;
            var idTrayectoria=$state.params.trayectoriaId;
            $scope.idHo=idHoja;
            $scope.deleteTrayectoria = function () {
            
                $http.delete(hojasContext+ "/"+idHoja+"/trayectorias/"+idTrayectoria, {}).then(function () {
                    $state.go('hojasDetail',({hojaId: idHoja}), {reload: true});
                });
            };
        }
    ]);
}
)(angular);