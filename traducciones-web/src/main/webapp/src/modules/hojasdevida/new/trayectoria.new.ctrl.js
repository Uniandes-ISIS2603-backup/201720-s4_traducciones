(function (ng) {
    var mod = ng.module("hojaModule");
    mod.controller('trayectoriasNewCtrl', ['$scope', '$http', '$state', '$rootScope',
        function ($scope, $http, $state) {

            var idHoja = $state.params.hojaId;

            $scope.addTrayectoria = function () {
                $http.post("api/hojadevida/" + idHoja + "/trayectorias", {
                    descripcion: $scope.trayectoriaDescripcion,
                    fechaFin: $scope.trayectoriaFechaF,
                    fechaInicio: $scope.trayectoriaFechaI,
                    name: $scope.trayectoriaName

                }).then(function () {
                    //Author created successfully
                    $state.go('hojasDetail', {hojaId: idHoja}, {reload: true});
                });
            };


        }
    ]);
}
)(angular);