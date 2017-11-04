(function (ng) {
    var mod = ng.module("empleadoModule");
    mod.constant("areaContext", "api/areasdeconocimiento");
    mod.controller('areadeconocimientoUpdateCtrl', ['$scope', '$http', 'areaContext', '$state',
        function ($scope, $http, areaContext, $state) {
            var idArea = $state.params.areadeconocimientoId;
            $http.get(areaContext + '/' + idArea ).then(function (response) {
                $scope.currentArea = response.data;
            });
            $scope.updateArea = function(){
            $http.put(areaContext + '/' + idArea, {
                name: $scope.currentArea.name,
                descripcion: $scope.currentArea.descripcion
            }).then(function (response) {
                //area de conocimiento updated successfully
                $state.go('empleadoAreas', {empleadoId: response.data.idEmpleado}, {reload: true});
            });
        };
    }
]);
}
)(angular);