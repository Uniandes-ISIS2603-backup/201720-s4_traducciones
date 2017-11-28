(function (ng) {
    var mod = ng.module("hojaModule", ['ui.router']);
    mod.constant("hojasContext", "api/hojadevida");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/hojasdevida/';
            var basePathIdioma = 'src/modules/idiomas/';
            $urlRouterProvider.otherwise("/hojasList");
            $stateProvider.state('hojas', {
                url: '/hojadevida',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'hojas.html',
                        controller: 'hojaCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('hojasList', {
                url: '/list',
                parent: 'hojas',
                views: {
                    'listView': {
                        templateUrl: basePath + 'hojas.list.html'
                    }
                },
                data: {
                    requireLogin: true,
                    roles: ['cliente', 'empleado']
                }                
            }).state('hojasDetail', {
                url: '/{hojaId:int}/detail',
                parent: 'hojas',
                param: {
                    hojaId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'hojas.detail.html',
                        controller: 'hojaCtrl',
                        controllerAs: 'ctrl'
                    }
                },
                data: {
                    requireLogin: true,
                    roles: ['empleado']
                }
            }).state('hojaCreate', {
                url: '/create',
                parent: 'hojas',
                views: {
                    'detailView': {
                        templateUrl: basePath + '/new/hojas.new.html',
                        controller: 'hojasNewCtrl'
                    }
                },
                data: {
                    requireLogin: true,
                    roles: ['empleado']
                }
            }).state('hojaUpdate', {
                url: '/update/{empleadoId:int}/{hojaId:int}',
                parent: 'hojas',
                param: {
                    empleadoId:null,
                    hojaId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/update/hojas.update.html',
                        controller: 'hojasUpdateCtrl'
                    }
                },
                data: {
                    requireLogin: true,
                    roles: ['empleado']
                }
            }).state('hojaDelete', {
                url: '/delete/{empleadoId:int}/{hojaId:int}',
                parent: 'hojas',
                param: {
                    empleadoId:null,
                    hojaId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/delete/hojas.delete.html',
                        controller: 'hojasDeleteCtrl'
                    }
                },
                data: {
                    requireLogin: true,
                    roles: ['empleado']
                }
            }).state('trayectoriaCreate', {
                url: '/create/empleado/{empleadoId:int}/hoja/{hojaId:int}/trayectorias',
                parent: 'hojas',
                param: {
                    hojaId: null,
                    empleadoId:null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/new/trayectoria.new.html',
                        controller: 'trayectoriasNewCtrl'
                    }
                },
                data: {
                    requireLogin: true,
                    roles: ['empleado']
                }
            }).state('trayectoriaDelete', {
                url: '/delete/empleado/{empleadoId:int}/hoja/{hojaId:int}/trayecorias/{trayectoriaId:int}',
                parent: 'hojas',
                param: {
                    hojaId: null,
                    trayectoriaId:null,
                    empleadoId:null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/delete/trayectorias.delete.html',
                        controller: 'trayectoriasDeleteCtrl'
                    }
                },
                data: {
                    requireLogin: true,
                    roles: ['empleado']
                }
            }).state('idiomaCreate', {
                url: '/create/empleado/{empleadoId:int}/hoja/{hojaId:int}/idiomas',
                parent: 'hojas',
                param: {
                    hojaId: null,
                    empleadoId:null
                },
                views: {
                    'detailView': {
                        templateUrl: basePathIdioma + '/new/idiomas.new.html',
                        controller: 'idiomasNewCtrl'
                    }
                },
                data: {
                    requireLogin: true,
                    roles: ['empleado']
                }
            }).state('idiomaDelete', {
                url: '/delete/empleado/{empleadoId:int}/hoja/{hojaId:int}/idiomas/{idiomaId:int}',
                parent: 'hojas',
                param: {
                    hojaId: null,
                    idiomaId:null,
                    empleadoId:null
                },
                views: {
                    'detailView': {
                        templateUrl: basePathIdioma + '/delete/idiomas.delete.html',
                        controller: 'idiomasDeleteCtrl'
                    }
                },
                data: {
                    requireLogin: true,
                    roles: ['empleado']
                }
            });
        }]);
})(window.angular);
