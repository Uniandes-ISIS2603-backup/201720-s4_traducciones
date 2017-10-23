/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {

    var mod = ng.module("ofertasModule");

    mod.controller("ofertasCtrl", ['$scope', '$state', '$stateParams', '$http', 'ofertasContext', function ($scope, $state, $stateParams, $http, context) {

            // inicialmente el listado de ofertas está vacio
            $scope.ofertasRecords = [];
            // carga las ofertas
            $http.get(context).then(function (response)
            {
                $scope.ofertasRecords = response.data;
            });
        }
    ]);
}
)(window.angular);