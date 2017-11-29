/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module("ofertasModule");
    mod.constant("ofertasContext", "api/ofertas");
    mod.controller('ofertaDelete2Ctrl', ['$scope', '$http', 'ofertasContext', '$state',
        function ($scope, $http, ofertasContext, $state) {
            var idOferta = $state.params.ofertaId;
            
            $scope.deleteOferta = function () {
               
                $http.delete(ofertasContext + '/' + idOferta, {}).then(function (response) {
                    $state.go('ofertasList', {reload: true});
                });
            };
        }
    ]);
}
)(angular);

