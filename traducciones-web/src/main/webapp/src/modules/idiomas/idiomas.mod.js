(function (ng) {
    var mod = ng.module("idiomaModule", ['ui.router']);
    mod.constant("idiomasContext", "api/idiomas");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/idiomas/';
            $urlRouterProvider.otherwise("/idiomasList");
            $stateProvider.state('idiomas', {
                url: '/idiomas',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'idiomas.html',
                        controller: 'idiomaCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('idiomasList', {
                url: '/list',
                parent: 'idiomas',
                views: {
                    'listView': {
                        templateUrl: basePath + 'idiomas.list.html'
                    }
                },
                data: {
                    requireLogin: false,
                    roles: ['cliente', 'empleado']
                }
            });
        }]);
})(window.angular);
