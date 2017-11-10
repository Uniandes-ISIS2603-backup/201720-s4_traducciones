(function (ng) {
    var mod = ng.module("idiomaModule");
    mod.constant("hojasContext", "api/hojadevida");
    mod.controller('idiomasDetailedCtrl', ['$scope', '$http', 'hojasContext', '$state',
        function ($scope, $http, hojasContext, $state) {
            $scope.desdeHoja=true;
            var idHoja=$state.params.hojaId;           

            if(idHoja!== undefined && idHoja !== null)
            {
            $http.get(hojasContext + '/' + idHoja + "/idiomas").then(function (response) {

                
                $scope.idIdioma = response.data.id;
                $scope.nombreIdioma = response.data.nombre;
                $scope.acronimoIdioma = response.data.acronimo;
                $scope.regionIdioma = response.data.region;                                

            });
        }
        }
    ]);
}
)(angular);