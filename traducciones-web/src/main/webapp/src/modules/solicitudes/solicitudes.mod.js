(function (ng) {
    var mod = ng.module("solicitudModule", ['ui.router']);
    mod.constant("solicitudesContext", "api/solicitudes");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/solicitudes/';
            var basePathAreas = 'src/modules/areasdeconocimiento/';
            $urlRouterProvider.otherwise("/clientesList");

            $stateProvider.state('solicitudes', {
                url: '/solicitudes',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'solicitudes.html',
                        controller: 'solicitudCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('solicitudDetail', {
                url: '/{solicitudId:int}/detail',
                parent: 'solicitudes',
                param: {
                    solicitudId: null
                },
                views: {
                    'listVieww':{
                        templateUrl: basePathAreas + 'areas.list.html',
                        controller: 'solicitudCtrl',
                        controllerAs: 'ctrl'
                    },
                    'detailView': {
                        templateUrl: basePath + 'solicitudes.detail.html',
                        controller: 'solicitudCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('solicitudCreate', {
                url: '/create',
                parent: 'solicitudes',
                views: {
                    'detailView': {
                        templateUrl: basePath + '/new/solicitudes.create.html',
                        controller: 'solicitudCreateCtrl'
                    }
                }
            }).state('solicitudUpdate', {
                url: '/update/{solicitudId:int}',
                parent: 'solicitudes',
                param: {
                    solicitudId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/new/solicitud.create.html',
                        controller: 'solicitudUpdateCtrl'
                    }
                }
            }).state('solicitudDelete', {
                url: '/delete/{solicitudId:int}',
                parent: 'solicitudes',
                param: {
                    solicitudId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/delete/solicitud.delete.html',
                        controller: 'solicitudDeleteCtrl'
                    }
                }
            });
        }]);
})(window.angular);


