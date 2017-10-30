(function (ng) {
    var mod = ng.module("hojaModule");
    mod.constant("hojasContext", "api/hojadevida");
    mod.controller('hojasNewCtrl', ['$scope', '$http', 'hojasContext', '$state', '$rootScope',
        function ($scope, $http, hojasContext, $state, $rootScope) {
            $rootScope.edit = false;
            $scope.createHoja = function () {
                $http.post(hojasContext, {
                    name: $scope.hojaName,
                    id:$scope.hojaId,
                    terminado: $scope.hojaTerminado
                }).then(function (response) {
                    //Author created successfully
                    $state.go('hojasList', {hojaId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);