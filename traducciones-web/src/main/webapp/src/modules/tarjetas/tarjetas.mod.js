(function (ng) {
    var mod = ng.module("tarjetaModule", ['ui.router']);
    mod.constant("tarjetasContext", "api/tarjetas");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/tarjetas/';
            $urlRouterProvider.otherwise("/tarjetasList");

            $stateProvider.state('tarjetas', {
                url: '/tarjetas',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'tarjetas.html',
                        controller: 'tarjetaCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('tarjetasList', {
                url: '/list',
                parent: 'tarjetas',
                views: {
                    'listView': {
                        templateUrl: basePath + 'tarjetas.list.html'
                    }
                }
            }).state('tarjetaDetail', {
                url: '/{tarjetaId:int}/detail',
                parent: 'tarjetas',
                param: {
                    tarjetaId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'tarjetas.detail.html',
                        controller: 'tarjetaCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('tarjetaCreate', {
                url: '/create',
                parent: 'tarjetas',
                views: {
                    'detailView': {
                        templateUrl: basePath + '/new/tarjetas.create.html',
                        controller: 'tarjetaCreateCtrl'
                    }
                }
            }).state('tarjetaUpdate', {
                url: '/tarjetaUpdate/{tarjetaId:int}',
                parent: 'tarjetas',
                param: {
                    tarjetaId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/new/tarjetas.create.html',
                        controller: 'tarjetaUpdateCtrl'
                    }
                }
            }).state('tarjetaDelete', {
                url: '/tarjetaDelete/{tarjetaId:int}',
                parent: 'tarjetas',
                param: {
                    tarjetaId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/delete/tarjetas.delete.html',
                        controller: 'tarjetaDeleteCtrl'
                    }
                }
            });
        }]);
})(window.angular);


