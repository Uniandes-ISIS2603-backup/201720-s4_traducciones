(function (ng) {
    var mod = ng.module("trabajoModule");
    mod.constant("trabajosContext", "api/trabajos");
    mod.constant("calificacionContext", "/calificaciones");
    mod.controller('trabajosNewCtrl', ['$scope', '$http', 'trabajosContext','calificacionContext', '$state', '$rootScope',
        function ($scope, $http, trabajosContext,calificacionContext, $state, $rootScope) {
            $rootScope.edit = false;
            $scope.createTrabajo = function () {
                $http.post(trabajosContext, {
                    name: $scope.trabajoName,
                    id:$scope.trabajoId,
                    terminado: $scope.trabajoTerminado
                }).then(function (response) {
                    //Author created successfully
                    $state.go('trabajosList', {trabajoId: response.data.id}, {reload: true});
                });
            };
            
            $scope.addCalificacion = function () {
                $http.post(calificacionContext, {
                    name: $scope.calificacionName,
                    id:$scope.calificacionId,
                    valor: $scope.calificacionValor,
                    comentario: $scope.calificacionComentario
                }).then(function (response) {
                    //Author created successfully
                    //$state.go('trabajosList', {trabajoId: response.data.id}, {reload: true});
                });
            };
            
            
        }
    ]);
}
)(angular);