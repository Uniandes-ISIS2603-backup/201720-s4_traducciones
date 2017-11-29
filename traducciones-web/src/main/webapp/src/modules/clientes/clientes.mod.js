(function (ng){
    var mod = ng.module("clienteModule", ['ui.router']);
    mod.constant("clientesContext", "api/clientes");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/clientes/';
            var basePathPago = 'src/modules/pagos/';
            var basePathTarjeta = 'src/modules/tarjetas/';
            $urlRouterProvider.otherwise("/clientesList");
            
            $stateProvider.state('clientes', {
                url: '/clientes',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'clientes.html',
                        controller: 'clienteCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('clientesList', {
                url: '/clienteslist',
                parent: 'clientes',
                views: {
                    'listView': {
                        templateUrl: basePath + 'clientes.list.html'
                    }
                }
            }).state('clientesDetail', {
                url: '/clientesDetail/{clienteId:int}',
                parent: 'clientes',
                param: {
                    clienteId: null
                },
                views: {
                    'listView': {
                        templateUrl: basePath + 'clientes.detail.html',
                        controller: 'clientesDetailCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('clientePagos', {
                url: '/clientePagos/{clienteId:int}',
                parent: 'clientes',
                param: {
                    clienteId: null
                },
                views: {
                    'listView': {
                        templateUrl: basePathPago + 'pagos.detailedList.html',
                        controller: 'pagosDetailedCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('clienteTarjetas', {
                url: '/clienteTarjetas/{clienteId:int}',
                parent: 'clientes',
                param: {
                    clienteId: null
                },
                views: {
                    'listView': {
                        templateUrl: basePathTarjeta + 'tarjetas.detail.html',
                        controller: 'tarjetasDetailedCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('clienteUpdate', {
                url: '/clienteUpdate/{clienteId:int}',
                parent: 'clientes',
                param: {
                    clienteId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'update/clientes.update.html',
                        controller: 'clienteUpdateCtrl'   
                    }
                }
            }).state('clienteDelete', {
                url: '/clienteDelete/{clienteId:int}',
                parent: 'clientes',
                param: {
                    clienteId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'delete/clientes.delete.html',
                        controller: 'clienteDeleteCtrl'
                    }
                } 
            }).state('clienteCreate', {
                url: '/clienteCreate',
                parent: 'clientes',
                views: {
                    'detailView': {
                        templateUrl: basePath + 'create/clientes.create.html',
                        controller: 'clienteCreateCtrl'
                    }
                }
            }).state('pagosDelete', {
                url: '/pagosDelete/{pagoId:int}',
                parent: 'clientes',
                param: {
                    pagoId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePathPago + 'delete/pagos.delete.html',
                        controller: 'pagosDeleteCtrl'
                    }
                } 
            }).state('pagosCreate', {
                url: '/pagosCreate/{clienteId:int}',
                parent: 'clientes',
                param: {
                    clienteId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePathPago + 'create/pagos.create.html',
                        controller: 'pagosCreateCtrl'
                    }
                } 
            }).state('pagosUpdate', {
                url: '/pagosUpdate/{pagoId:int}',
                parent: 'clientes',
                param: {
                    pagoId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePathPago + 'update/pagos.update.html',
                        controller: 'pagosUpdateCtrl'
                    }
                } 
            }).state('tarjetasDelete', {
                url: '/tarjetasDelete/{tarjetaId:int}',
                parent: 'clientes',
                param: {
                    tarjetaId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePathTarjeta + 'delete/tarjetas.delete.html',
                        controller: 'tarjetasDeleteCtrl'
                    }
                } 
            }).state('tarjetasCreate', {
                url: '/tarjetasCreate/{clienteId:int}',
                parent: 'clientes',
                param: {
                    clienteId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePathTarjeta + 'create/tarjetas.create.html',
                        controller: 'tarjetasCreateCtrl'
                    }
                } 
            }).state('tarjetasUpdate', {
                url: '/tarjetasUpdate/{tarjetaId:int}',
                parent: 'clientes',
                param: {
                    pagoId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePathTarjeta + 'update/tarjetas.update.html',
                        controller: 'tarjetasUpdateCtrl'
                    }
                } 
            });
        }]);
})(window.angular);
