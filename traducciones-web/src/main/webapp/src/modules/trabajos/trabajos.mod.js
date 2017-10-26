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
            });
        }]);
})(window.angular);
