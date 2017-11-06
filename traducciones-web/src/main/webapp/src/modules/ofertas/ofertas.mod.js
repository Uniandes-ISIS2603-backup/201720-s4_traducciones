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
            }).state('hojaCreate', {
                url: '/create',
                parent: 'hojas',
                views: {
                    'detailView': {
                        templateUrl: basePath + '/new/hojas.new.html',
                        controller: 'hojasNewCtrl'
                    }
                }
            }).state('hojaUpdate', {
                url: '/update/{hojaId:int}',
                parent: 'hojas',
                param: {
                    hojaId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/new/hojas.new.html',
                        controller: 'hojasUpdateCtrl'
                    }
                }
            }).state('hojaDelete', {
                url: '/delete/{hojaId:int}',
                parent: 'hojas',
                param: {
                    hojaId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/delete/hojas.delete.html',
                        controller: 'hojasDeleteCtrl'
                    }
                }
            });
        }]);
})(window.angular);
