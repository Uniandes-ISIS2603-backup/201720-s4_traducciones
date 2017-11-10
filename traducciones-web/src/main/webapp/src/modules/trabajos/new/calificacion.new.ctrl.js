(function (ng) {
    var mod = ng.module("trabajoModule");
    mod.constant("trabajosContext", "api/trabajos");
    mod.controller('calificacionesNewCtrl', ['$scope', '$http', 'trabajosContext', '$state', '$rootScope',
        function ($scope, $http, trabajosContext, $state) {
            
            var idTrabajo = $state.params.trabajoId;
            
            $scope.addCalificacion = function () {
                $http.post(trabajosContext+"/"+idTrabajo+"/calificacion", {
                    name: $scope.calificacionName,
                    valor: $scope.calificacionValor,
                    comentario: $scope.calificacionComentario
                }).then(function () {
                    //Author created successfully
                    $state.go('trabajosDetail', {trabajoId: idTrabajo}, {reload: true});
                });
            };
            
            
        }
    ]);
}
)(angular);