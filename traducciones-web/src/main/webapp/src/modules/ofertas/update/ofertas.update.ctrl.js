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

                        $scope.id = response.data.id;
                        $scope.nombre = response.data.nombre;
                        $scope.descripcion = response.data.descripcion;
                        $scope.fechaVigencia = response.data.fechaVigencia;
                        $scope.codigo = response.data.codigo;
                        $scope.cantidadInicial = response.data.cantidadInicial;
                        $scope.cantidadActual = response.data.cantidadActual;

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
