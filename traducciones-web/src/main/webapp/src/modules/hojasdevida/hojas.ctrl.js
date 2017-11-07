(function (ng) {
    var mod = ng.module("hojaModule");
    mod.constant("hojaContext", "api/hojadevida");
    mod.controller('hojaCtrl', ['$scope', '$http', 'hojaContext', '$state',
        function ($scope, $http, hojaContext, $state) {
            
            if ($state.params.hojaId !== undefined && $state.params.hojaId !== null) {
                $http.get(hojaContext + '/' + $state.params.hojaId).then(function (response) {
                    
            $scope.idHojaDeVida = response.data.id;
                $scope.nombreHojaDeVida = response.data.name;
                $scope.descripcionHojaDeVida = response.data.descripcion;
                $scope.formacionHojaDeVida = response.data.formacionAcademica;
                $scope.perfilHojaDeVida = response.data.perfilProfesional;
                $scope.trayectoriasHoja=response.data.trayectorias;
               
                    
                });
            }
            
            $http.get(hojaContext).then(function (response) {
                $scope.hojasRecords = response.data;
                    
            });
            
            
            
            
        }
    ]);
}
)(angular);