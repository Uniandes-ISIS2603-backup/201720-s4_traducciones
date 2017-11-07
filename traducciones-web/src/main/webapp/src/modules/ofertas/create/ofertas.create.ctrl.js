/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module("ofertasModule");
    mod.constant("ofertasContext", "api/empleados");
    mod.controller('ofertasNewCtrl', ['$scope', '$http', 'ofertasContext', '$state', '$rootScope',
        function ($scope, $http, ofertasContext, $state, $rootScope) {
            $rootScope.edit = false;
            var idEmpleado = $state.params.idEmpleado;
            $scope.createOferta = function () {
                $http.post(ofertasContext+ '/'+idEmpleado+ '/'+'ofertas', {
                    nombre: $scope.ofertaName,
                    id:$scope.ofertaId,
                    descripcion: $scope.ofertaDescripcion,
                    fechaVigencia: $scope.ofertaFechaVigencia,
                    codigo: $scope.ofertaCodigo,
                    cantidadInicial: $scope.ofertaCantidadInicial,
                    cantidadActual: $scope.ofertaCantidadActual
                }).then(function (response) {
                    //Oferta created successfully
                    $state.go('ofertasList', {ofertaId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);

