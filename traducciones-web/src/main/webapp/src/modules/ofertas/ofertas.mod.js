/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(function (ng) {
var mod = ng.module("ofertasModule", []);
    mod.constant("ofertasContext", "api/ofertas");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/ofertas/';

            $stateProvider.state('listaOfertas', {
                url: '/ofertas',
                views: {
                    'mainView': {
                        controller: 'ofertasCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'ofertas.list.html'
                    }
                }
            });
        }]);

})(window.angular);


