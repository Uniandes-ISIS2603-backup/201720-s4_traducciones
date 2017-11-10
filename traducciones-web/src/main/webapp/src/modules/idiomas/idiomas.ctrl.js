(function (ng) {
    var mod = ng.module("idiomaModule");
    mod.constant("idiomasContext", "api/idiomas");
    mod.controller('idiomaCtrl', ['$scope', '$http', 'idiomasContext', '$state',
        function ($scope, $http, idiomasContext, $state) {
            $scope.desdeHojaDeVida=false;
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
            
            $http.get(idiomasContext).then(function (response) {
                $scope.idiomasRecords = response.data;
                    
            });
            
            
            
            
        }
    ]);
}
)(angular);