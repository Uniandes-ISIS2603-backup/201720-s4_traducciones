/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {

    var mod = ng.module("ofertaModule");

    mod.controller("ofertasCtrl", ['$scope', '$state', '$stateParams', '$http', 'ofertasContext', function ($scope, $state, $stateParams, $http, context) {

            // inicialmente el listado de ofertas está vacio
            $scope.records = [];
            // carga las ofertas
            $http.get(context + '/' + id ).then(function (response) {
                $scope.records = response.data;
            });


        }]);
})(window.angular);