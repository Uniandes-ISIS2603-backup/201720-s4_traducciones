(function (ng) {
    var mod = ng.module("idiomaModule");
    mod.controller('idiomasNewCtrl', ['$scope', '$http', '$state', '$rootScope',
        function ($scope, $http, $state, $rootScope) {
            $rootScope.edit = false;
            var idHoja=$state.params.hojaId;
            
            $scope.createIdioma = function () {
                $http.post("api/hojadevida/"+idHoja+"/idiomas", {
                    nombre: $scope.idiomaNombre,                    
                    acronimo: $scope.idiomaAcronimo,
                    region: $scope.idiomaRegion                    
                }).then(function () {
                    
                    $state.go('hojasDetail', {hojaId: idHoja}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);