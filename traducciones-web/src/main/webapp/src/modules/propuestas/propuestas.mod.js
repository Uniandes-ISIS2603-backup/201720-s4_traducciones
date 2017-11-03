/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(function (ng) {
var mod = ng.module("propuestasModule", []);
    mod.constant("propuestasContext", "api/propuestas");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/propuestas/';

            $stateProvider.state('listaPropuestas', {
                url: '/propuestas',
                views: {
                    'mainView': {
                        controller: 'propuestasCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'propuestas.list.html'
                    }
                }
            });
        }]);

})(window.angular);



