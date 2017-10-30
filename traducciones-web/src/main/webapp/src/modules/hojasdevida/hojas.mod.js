(function (ng) {
    var mod = ng.module("hojaModule", ['ui.router']);
    mod.constant("hojasContext", "api/hojas");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/hojasdevida/';
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
                }
            }).state('hojaCreate', {
                url: '/create',
                parent: 'hojas',
                views: {
                    'detailView': {
                        templateUrl: basePath + '/new/hojas.new.html',
                        controller: 'hojasNewCtrl'
                    }
                }
            }).state('hojaUpdate', {
                url: '/update/{hojaId:int}',
                parent: 'hojas',
                param: {
                    hojaId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/new/hojas.new.html',
                        controller: 'hojasUpdateCtrl'
                    }
                }
            }).state('hojaDelete', {
                url: '/delete/{hojaId:int}',
                parent: 'hojas',
                param: {
                    hojaId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/delete/hojas.delete.html',
                        controller: 'hojasDeleteCtrl'
                    }
                }
            });
        }]);
})(window.angular);
