(function (ng) {
    var mod = ng.module("hojaModule");
    mod.controller('hojasNewCtrl', ['$scope', '$http', '$state', '$rootScope',
        function ($scope, $http, $state, $rootScope) {
            $rootScope.edit = false;
            
            
            $scope.createHoja = function () {
                $http.post("api/empleados/"+$state.params.empleadoId+"/hojadevida", {
                    name: $scope.hojaName,
                    id:$scope.hojaId,
                    descripcion: $scope.hojaDescripcion,
                    perfilProfesional: $scope.hojaPerfilProfesional,
                    formacionAcademica: $scope.hojaFormacionAcademica
                }).then(function (response) {
                    //Author created successfully
                    $state.go('hojasList', {hojaId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);