(function (ng) {
    var mod = ng.module("idiomaModule");
    mod.constant("hojasContext", "api/hojadevida");
    mod.controller('idiomasDeleteCtrl', ['$scope', '$http', 'hojasContext', '$state',
        function ($scope, $http, hojasContext, $state) {
           
            var idIdioma = $state.params.idiomaId;
            var idHoja=$state.params.hojaId;
            var idEmpleado=$state.params.empleadoId;
            $scope.idEmp=idEmpleado;
            $scope.idHoj=idHoja;
            $scope.deleteIdioma = function () {
                $http.delete(hojasContext + '/' + idHoja+ "/idiomas/"+idIdioma, {}).then(function () {
                    
                    $state.go('empleadoHojas', {empleadoId:idEmpleado,empleadoHoja: idHoja}, {reload: true});
                    
                });
            };
        }
    ]);
}
)(angular);