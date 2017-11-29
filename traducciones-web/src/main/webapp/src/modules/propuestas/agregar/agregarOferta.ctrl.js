

(function (ng) {
    var mod = ng.module("propuestasModule");
    mod.constant("propuestasContext", "api/propuestas");
    mod.controller('agregarOfertaCtrl', ['$scope', '$http', 'propuestasContext', '$state', '$rootScope',
        function ($scope, $http, propuestasContext, $state, $rootScope) {
            $rootScope.edit = false;
            var idPropuesta = $state.params.propuestaId;
            $scope.createPropuesta = function () {
                $http.put(propuestasContext+ '/'+idPropuesta+ '/'+'propuestas', {
                    nombre: $scope.propuestaName,
                    costo: $scope.propuestaCosto,
                    estado: "EN_REVISION"
                }).then(function (response) {
                    //Propuesta created successfully
                    $state.go('propuestasList', {propuestaId: idPropuesta}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


