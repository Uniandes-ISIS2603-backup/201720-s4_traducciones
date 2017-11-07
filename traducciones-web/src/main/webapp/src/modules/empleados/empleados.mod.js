(function (ng) {
    var mod = ng.module("empleadoModule", ['ui.router']);
    mod.constant("empleadosContext", "api/empleados");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/empleados/';
            var basePathArea = 'src/modules/areasdeconocimiento/';
            var basePathHoja = 'src/modules/hojasdevida/';
            var basePathOferta = 'src/modules/ofertas/';
            $urlRouterProvider.otherwise("/empleadosList");
            
            $stateProvider.state('empleados', {
                url: '/empleados',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'empleados.html',
                        controller: 'empleadoCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('empleadosList', {
                url: '/empleadoslist',
                parent: 'empleados',
                views: {
                    'listView': {
                        templateUrl: basePath + 'empleados.list.html'
                    }
                }
            }).state('empleadoAreas', {
                url: '/empleadoareas/{empleadoId:int}',
                parent: 'empleados',
                param: {
                    empleadoId: null
                },
                views: {
                    'listView': {
                        templateUrl: basePathArea + 'areasdeconocimiento.detailedList.html',
                        controller: 'areasDetailedCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('empleadoHojas', {
                url: '/empleadohojas/{empleadoId:int}/{empleadoHoja:int}',
                parent: 'empleados',
                param: {
                    empleadoId: null,
                    empleadoHoja:null
                },
                views: {
                    'listView': {
                        templateUrl: basePathHoja + 'hojas.detail.html',
                        controller: 'hojasDetailedCtrl',
                        controllerAs: 'ctrl'
                    }
                }

            }).state('empleadoOfertas', {
                url: '/empleadoOfertas/{empleadoId:int}',
                parent: 'empleados',
                param: {
                    empleadoId: null
                },
                views: {
                    'listView': {
                        templateUrl: basePathOferta + 'ofertas.detail.html',
                        controller: 'ofertasDetailCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('empleadoOfertasCreate', {
                url: '/empleadoOfertas/{empleadoId:int}/crear',
                parent: 'empleados',
                param: {
                    empleadoId: null
                },
                views: {
                    'listView': {
                        templateUrl: basePathOferta + 'create/ofertas.create.html',
                        controller: 'ofertasNewCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('empleadoHojasCreate', {
                url: '/empleadohojas/{empleadoId:int}/crear',
                parent: 'empleados',
                param: {
                    empleadoId: null
                },
                views: {
                    'listView': {

                        templateUrl: basePathOferta + 'ofertas.detail.html',
                        controller: 'hojasNewCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('empleadoUpdate', {
                url: '/empleadoupdate/{empleadoId:int}',
                parent: 'empleados',
                param: {
                    empleadoId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/update/empleados.update.html',
                        controller: 'empleadoUpdateCtrl'   
                    }
                }
            }).state('empleadoDelete', {
                url: '/empleadodelete/{empleadoId:int}',
                parent: 'empleados',
                param: {
                    empleadoId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/delete/empleados.delete.html',
                        controller: 'empleadoDeleteCtrl'
                    }
                } 
            }).state('empleadoCreate', {
                url: '/empleadocreate',
                parent: 'empleados',
                views: {
                    'detailView': {
                        templateUrl: basePath + '/create/empleados.create.html',
                        controller: 'empleadoCreateCtrl'
                    }
                }
            }).state('areadeconocimientoDelete', {
                url: '/areadelete/{areadeconocimientoId:int}',
                parent: 'empleados',
                param: {
                    areadeconocimientoId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePathArea + '/delete/areasdeconocimiento.delete.html',
                        controller: 'areadeconocimientoDeleteCtrl'
                    }
                } 
            }).state('areadeconocimientoCreate', {
                url: '/areacreate/{empleadoId:int}',
                parent: 'empleados',
                param: {
                    empleadoId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePathArea + '/create/areasdeconocimiento.create.html',
                        controller: 'areadeconocimientoCreateCtrl'
                    }
                } 
            }).state('areadeconocimientoUpdate', {
                url: '/areaupdate/{areadeconocimientoId:int}',
                parent: 'empleados',
                param: {
                    areadeconocimientoId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePathArea + '/update/areasdeconocimiento.update.html',
                        controller: 'areadeconocimientoUpdateCtrl'
                    }
                } 
            });
        }]);
})(window.angular);