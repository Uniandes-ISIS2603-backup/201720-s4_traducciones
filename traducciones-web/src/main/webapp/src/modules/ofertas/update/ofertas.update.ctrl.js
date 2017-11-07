/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(
        function (ng) {
            var mod = ng.module("ofertasModule");
            mod.constant("ofertasContext", "api/empleados");
            mod.controller('ofertasUpdateCtrl', ['$scope', '$http', 'ofertasContext', '$state', '$rootScope',
                function ($scope, $http, ofertasContext, $state, $rootScope) {
                    $rootScope.edit = true;

                    var idEmpleado = $state.params.idEmpleado;

                    //Consulto el autor a editar.
                    $http.get(ofertasContext + '/' + idEmpleado + '/' + 'ofertas').then(function (response) {

                        $scope.ofertasRecords = response.data;
                    });


                    $scope.newBooks = function () {
                        $scope.allBooksAuthor = [];
                        for (var ite in idsBook) {
                            for (var all in $scope.Allbooks) {
                                if ($scope.Allbooks[all].id === parseInt(idsBook[ite])) {
                                    $scope.allBooksAuthor.push($scope.Allbooks[all]);
                                }
                            }
                        }
                    };
                }
            ]);
        }
)(angular);
