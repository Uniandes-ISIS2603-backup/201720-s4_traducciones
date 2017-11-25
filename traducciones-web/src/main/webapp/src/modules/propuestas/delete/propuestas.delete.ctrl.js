/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module("propuestasModule");
    mod.constant("propuestasContext", "api/propuestas");
    mod.controller('propuestaDeleteCtrl', ['$scope', '$http', 'propuestasContext', '$state',
        function ($scope, $http, ofertasContext, $state) {
            var idPropuesta = $state.params.propuestaId;
            
            $scope.deletePropuesta = function () {
               
                $http.delete(ofertasContext + '/' + idPropuesta, {}).then(function (response) {
                    $state.go('empleadoPropuestas', {empleadoId: $state.params.empleadoId}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);
