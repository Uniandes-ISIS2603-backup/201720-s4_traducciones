(function (ng) {
    var mod = ng.module("trabajoModule", ['ui.router']);
    mod.constant("trabajosContext", "api/trabajos");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/trabajos/';
            $urlRouterProvider.otherwise("/trabajosList");
            $stateProvider.state('trabajos', {
                url: '/trabajos',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'trabajos.html',
                        controller: 'trabajoCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('trabajosList', {
                url: '/list',
                parent: 'trabajos',
                views: {
                    'listView': {
                        templateUrl: basePath + 'trabajos.list.html'
                    }
                }
            }).state('trabajosDetail', {
                url: '/{trabajoId:int}/detail',
                parent: 'trabajos',
                param: {
                    trabajoId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'trabajos.detail.html',
                        controller: 'trabajoCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('trabajoCreate', {
                url: '/create',
                parent: 'trabajos',
                views: {
                    'detailView': {
                        templateUrl: basePath + '/new/trabajos.new.html',
                        controller: 'trabajosNewCtrl'
                    }
                }
            }).state('trabajoUpdate', {
                url: '/update/{trabajoId:int}',
                parent: 'trabajos',
                param: {
                    trabajoId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/new/trabajos.new.html',
                        controller: 'trabajosUpdateCtrl'
                    }
                }
            }).state('trabajoDelete', {
                url: '/delete/{trabajoId:int}',
                parent: 'trabajos',
                param: {
                    trabajoId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/delete/trabajos.delete.html',
                        controller: 'trabajosDeleteCtrl'
                    }
                }
            }).state('calificacionCreate', {
                url: '/create/{trabajoId:int}/calificacion',
                parent: 'trabajos',
                param: {
                    trabajoId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/new/calificacion.new.html',
                        controller: 'calificacionesNewCtrl'
                    }
                }
            }).state('calificacionDelete', {
                url: '/create/{trabajoId:int}/calificacion/{calificacionId:int}',
                parent: 'trabajos',
                param: {
                    trabajoId: null,
                    calificacionId:null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/delete/calificaciones.delete.html',
                        controller: 'calificacionesDeleteCtrl'
                    }
                }
            });
        }]);
})(window.angular);
