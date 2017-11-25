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

            $stateProvider.state('propuestas', {
                url: '/propuestas',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'propuestas.html',
                        controller: 'propuestasCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('propuestasList', {
                url: '/list',
                parent: 'propuestas',
                views: {
                    'listView': {
                        templateUrl: basePath + 'propuestas.list.html'
                    }
                }
            }).state('propuestasDetail', {
                url: '/{propuestaId:int}/detail',
                parent: 'propuestas',
                param: {
                    propuestaId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'propuestas.detail.html',
                        controller: 'propuestasCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('propuestaCreate', {
                url: '/create/{empleadoId: int}',
                parent: 'propuestas',
                param: {
                    empleadoId: null
                },views: {
                    'detailView': {
                        templateUrl: basePath + '/create/propuestas.create.html',
                        controller: 'propuestasNewCtrl'
                    }
                }
            }).state('propuestaDelete', {
                url: '/delete/{propuestaId:int}/{empleadoId: int}',
                parent: 'propuestas',
                param: {
                    propuestaId: null,
                    empleadoId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/delete/propuestas.delete.html',
                        controller: 'propuestaDeleteCtrl'
                    }
                }
            });
        }]);
})(window.angular);
