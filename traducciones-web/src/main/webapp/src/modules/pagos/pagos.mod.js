(function (ng) {
    var mod = ng.module("pagoModule", ['ui.router']);
    mod.constant("pagosContext", "api/pagos");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/pagos/';
            $urlRouterProvider.otherwise("/pagosList");

            $stateProvider.state('pagos', {
                url: '/pagos',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'pagos.html',
                        controller: 'pagoCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('pagosList', {
                url: '/list',
                parent: 'pagos',
                views: {
                    'listView': {
                        templateUrl: basePath + 'pagos.list.html',
                        controller: 'pagoCtrl',
                        controllerAs: 'ctrl'
                    }
                },data: {
                    requireLogin: true,
                    roles: ['cliente', 'empleado']
                }
            }).state('pagoDetail', {
                url: '/{pagoId:int}/detail',
                parent: 'pagos',
                param: {
                    pagoId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'pagos.detail.html',
                        controller: 'pagoCtrl',
                        controllerAs: 'ctrl'
                    }
                },data: {
                    requireLogin: true,
                    roles: ['cliente']
                }
            }).state('pagoCreate', {
                url: '/create',
                parent: 'pagos',
                views: {
                    'detailView': {
                        templateUrl: basePath + '/new/pagos.create.html',
                        controller: 'pagoCreateCtrl'
                    }
                },data: {
                    requireLogin: true,
                    roles: ['cliente']
                }
            }).state('pagoUpdate', {
                url: '/pagoUpdate/{pagoId:int}',
                parent: 'pagos',
                param: {
                    pagoId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/new/pagos.create.html',
                        controller: 'pagoUpdateCtrl'
                    }
                },data: {
                    requireLogin: true,
                    roles: ['cliente']
                }
            }).state('pagoDelete', {
                url: '/pagoDelete/{pagoId:int}',
                parent: 'pagos',
                param: {
                    pagoId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/delete/pagos.delete.html',
                        controller: 'pagoDeleteCtrl'
                    }
                },data: {
                    requireLogin: true,
                    roles: ['cliente']
                }
            });
        }]);
})(window.angular);


