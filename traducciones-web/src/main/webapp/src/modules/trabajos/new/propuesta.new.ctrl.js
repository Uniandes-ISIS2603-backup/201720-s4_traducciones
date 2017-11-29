(function (ng) {
    var mod = ng.module("trabajoModule");
    mod.constant("trabajosContext", "api/trabajos");
    mod.controller('propuestasNewCtrl', ['$scope', '$http', 'trabajosContext', '$state', '$rootScope',
        function ($scope, $http, trabajosContext, $state) {
            
            var idTrabajo = $state.params.trabajoId;
            var idPropuesta = $scope.propuestaId;
            
            $scope.addPropuesta = function () {
                $http.post(trabajosContext+"/"+idTrabajo+"/propuesta/"+idPropuesta).then(function () {
                    //Author created successfully
                    $state.go('trabajosDetail', {trabajoId: idTrabajo}, {reload: true});
                });
            };
            
            
        }
    ]);
}
)(angular);