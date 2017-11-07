/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(
        function (ng) {
            var mod = ng.module("ofertasModule");
            mod.constant("ofertasContext", "api/ofertas");
            mod.controller('ofertaUpdateCtrl', ['$scope', '$http', 'ofertasContext', '$state', '$rootScope',
                function ($scope, $http, ofertasContext, $state, $rootScope) {
                    $rootScope.edit = true;

                    var idOferta = $state.params.idOferta;

                    //Consulto la oferta a editar.
                    $http.get(ofertasContext + '/' + idOferta).then(function (response) {

                        $scope.oferta = response.data;
                        console.log(response.data.id);
                    });

                    $scope.updateOferta = function () {
                        $http.put(ofertasContext + '/' + idOferta, {
                            nombre: $scope.ofertaName,
                            id: $scope.ofertaId,
                            descripcion: $scope.ofertaDescripcion,
                            fechaVigencia: $scope.ofertaFechaVigencia,
                            codigo: $scope.ofertaCodigo,
                            cantidadInicial: $scope.ofertaCantidadInicial,
                            cantidadActual: $scope.ofertaCantidadActual
                        }).then(function (response) {
                            $state.go('ofertasList', {reload: true});

                        });
                    }
                }
            ]);
        }
)(angular);
