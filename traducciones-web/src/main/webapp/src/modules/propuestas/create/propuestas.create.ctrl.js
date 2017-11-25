/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module("propuestasModule");
    mod.constant("empleadooContext", "api/empleados");
    mod.controller('propuestasNewCtrl', ['$scope', '$http', 'empleadooContext', '$state', '$rootScope',
        function ($scope, $http, empleadooContext, $state, $rootScope) {
            $rootScope.edit = false;
            var idEmpleado = $state.params.empleadoId;
            $scope.createPropuesta = function () {
                $http.post(empleadooContext+ '/'+idEmpleado+ '/'+'propuestas', {
                    nombre: $scope.propuestaName,
                    costo: $scope.propuestaCosto,
                    estado: "EN_REVISION"
                }).then(function (response) {
                    //Propuesta created successfully
                    $state.go('empleadoPropuestas', {empleadoId: idEmpleado}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);

