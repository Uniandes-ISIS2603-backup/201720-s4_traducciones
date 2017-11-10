(function (ng) {
    var mod = ng.module("trabajoModule");
    mod.constant("trabajosContext", "api/trabajos");
    mod.controller('calificacionesDeleteCtrl', ['$scope', '$http', 'trabajosContext', '$state',
        function ($scope, $http, trabajosContext, $state) {
            var idTrabajo = $state.params.trabajoId;
            var idCalificacion=$state.params.calificacionId;
            $scope.idTr=idTrabajo;
            $scope.deleteCalificacion = function () {
            
                $http.delete(trabajosContext + '/' + idTrabajo+"/calificacion/"+idCalificacion, {}).then(function () {
                    $state.go('trabajosDetail',({trabajoId: idTrabajo}), {reload: true});
                });
            };
        }
    ]);
}
)(angular);