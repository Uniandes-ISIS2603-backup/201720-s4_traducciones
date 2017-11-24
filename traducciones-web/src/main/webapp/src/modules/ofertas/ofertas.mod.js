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

            $stateProvider.state('ofertas', {
                url: '/ofertas',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'ofertas.html',
                        controller: 'ofertasCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('ofertasList', {
                url: '/list',
                parent: 'ofertas',
                views: {
                    'listView': {
                        templateUrl: basePath + 'ofertas.list.html'
                    }
                }
            }).state('ofertasDetail', {
                url: '/{ofertaId:int}/detail',
                parent: 'ofertas',
                param: {
                    ofertaId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'ofertas.detail.html',
                        controller: 'ofertasCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('ofertaCreate', {
                url: '/create}',
                parent: 'ofertas',
                views: {
                    'detailView': {
                        templateUrl: basePath + '/create/ofertas.create.html',
                        controller: 'ofertasNewCtrl'
                    }
                }
            }).state('ofertaDelete', {
                url: '/delete/{ofertaId:int}',
                parent: 'ofertas',
                param: {
                    hojaId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/delete/ofertas.delete.html',
                        controller: 'ofertaDeleteCtrl'
                    }
                }
            });
        }]);
})(window.angular);
