/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module("ofertasModule");
    mod.constant("empleadooContext", "api/empleados");
    mod.controller('ofertasNewCtrl', ['$scope', '$http', 'empleadooContext', '$state', '$rootScope',
        function ($scope, $http, empleadooContext, $state, $rootScope) {
            $rootScope.edit = false;
            var idEmpleado = $state.params.empleadoId;
            $scope.createOferta = function () {
                $http.post(empleadooContext+ '/'+idEmpleado+ '/'+'ofertas', {
                    nombre: $scope.ofertaName,
                    id: $scope.ofertaId,
                    descripcion: $scope.ofertaDescripcion,
                    fechaVigencia: $scope.fechaVigencia,
                    codigo: $scope.ofertaCodigo,
                    cantidadInicial: $scope.ofertaCantidadInicial,
                    cantidadActual: $scope.ofertaCantidadActual
                }).then(function (response) {
                    //Oferta created successfully
                    $state.go('empleadoOfertas', {empleadoId: idEmpleado}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);

