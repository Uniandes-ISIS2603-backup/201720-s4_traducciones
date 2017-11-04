(function (ng) {
    var mod = ng.module("empleadoModule");
    mod.controller('areadeconocimientoCreateCtrl', ['$scope', '$http', '$state',
        function ($scope, $http, $state) {
            $scope.idempleado = $state.params.empleadoId;
            $scope.createArea = function () {
                $http.post('api/empleados/' + $state.params.empleadoId + '/areasdeconocimiento', {
                    name: $scope.areaName,
                    descripcion: $scope.areaDescripcion
                }).then(function (response) {
                    //area de conocimiento created successfully
                    $state.go('empleadoAreas', {empleadoId: response.data.idEmpleado}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);