(function (ng) {
    var mod = ng.module("hojaModule");
    mod.constant("hojasContext", "api/hojadevida");
    mod.controller('hojasDeleteCtrl', ['$scope', '$http', 'hojasContext', '$state',
        function ($scope, $http, hojasContext, $state) {
            var idHoja = $state.params.hojaId;
            
            $scope.deleteHoja = function () {
               
                $http.delete(hojasContext + '/' + idHoja, {}).then(function (response) {
                    $state.go('hojasList', {hojaId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);