(function (ng) {
    var mod = ng.module("empleadoModule", ['ui.router']);
    mod.constant("empleadosContext", "api/empleados");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/empleados/';
            var basePathAreaDeConocimiento = 'src/modules/areasdeconocimiento/';
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
                        templateUrl: basePathAreaDeConocimiento + 'areasdeconocimiento.detailedList.html',
                        controller: 'areasDetailedCtrl',
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
                        controller: 'empleadoUpdateCtrl',
                        
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
            });
        }]);
})(window.angular);