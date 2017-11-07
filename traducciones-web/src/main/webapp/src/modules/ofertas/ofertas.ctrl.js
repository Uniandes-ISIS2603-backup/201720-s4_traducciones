(function (ng) {
    var mod = ng.module("ofertasModule");
    mod.constant("ofertasContext", "api/ofertas");
    mod.controller('ofertasCtrl', ['$scope', '$http', 'ofertasContext',
        
        
            function ($scope, $http, ofertasContext) {
                
                $scope.esEmpleado = true;
            $http.get(ofertasContext).then(function (response) {
                $scope.ofertasRecords = response.data;
            });
            }
        ]);
}
)(angular);