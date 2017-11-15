(function (ng) {
    var mod = ng.module("hojaModule");
    mod.constant("hojasContext", "api/hojadevida");
    mod.controller('trayectoriasDeleteCtrl', ['$scope', '$http', 'hojasContext', '$state',
        function ($scope, $http, hojasContext, $state) {
           var idTrayectoria = $state.params.trayectoriaId;
            var idHoja=$state.params.hojaId;
            var idEmpleado=$state.params.empleadoId;
            $scope.idEmp=idEmpleado;
            $scope.idHoj=idHoja;
            
            $scope.deleteTrayectoria = function () {
            
                $http.delete(hojasContext+ "/"+idHoja+"/trayectorias/"+idTrayectoria, {}).then(function () {
                    $state.go('empleadoHojas', {empleadoId:idEmpleado,empleadoHoja: idHoja}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);