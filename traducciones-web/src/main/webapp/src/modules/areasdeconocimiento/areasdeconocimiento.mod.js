(function (ng) {
    var mod = ng.module("areadeconocimientoModule", ['ui.router']);
    mod.constant("areasContext", "api/areasdeconocimiento");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/areasdeconocimiento/';
            $urlRouterProvider.otherwise("/areasdeconocimientoList");
            
            $stateProvider.state('areasdeconocimiento', {
                url: '/areasdeconocimiento',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'areasdeconocimiento.html',
                        controller: 'areadeconocimientoCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('areasList', {
                url: '/areasList',
                parent: 'areasdeconocimiento',
                views: {
                    'listView': {
                        templateUrl: basePath + 'areasdeconocimiento.list.html'
                    }
                }
            }).state('areadeconocimientoDelete', {
                url: '/delete/{areadeconocimientoId:int}',
                parent: 'areasdeconocimiento',
                param: {
                    areadeconocimientoId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/delete/areasdeconocimiento.delete.html',
                        controller: 'areadeconocimientoDeleteCtrl'
                    }
                }  
            });
        }]);
})(window.angular);