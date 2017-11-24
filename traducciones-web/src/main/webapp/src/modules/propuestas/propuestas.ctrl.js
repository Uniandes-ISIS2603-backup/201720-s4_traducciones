/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {

    var mod = ng.module("propuestasModule");

    mod.controller("propuestasCtrl", ['$scope', '$state', '$stateParams', '$http', 'propuestasContext', function ($scope,$http, propuestasContext) {

            // inicialmente el listado de ofertas está vacio
            $scope.propuestasRecords = [];
            // carga las ofertas
            $http.get(propuestasContext).then(function (response)
            {
                $scope.propuestasRecords = response.data;
            });
        }
    ]);
}
)(window.angular);

