(function (ng) {
    var mod = ng.module("empleadoModule");
    mod.constant("areasdeconocimientoContext", "api/areasdeconocimiento");
    mod.controller('areadeconocimientoDeleteCtrl', ['$scope', '$http', 'areasdeconocimientoContext', '$state',
        function ($scope, $http, areasdeconocimientoContext, $state) {
            var idAreadeconocimiento = $state.params.areadeconocimientoId;
            var idempleado;
            $http.get(areasdeconocimientoContext + '/' + idAreadeconocimiento, {}).then(function (response){
                
                $scope.idempleado = response.data.idEmpleado; //variable para poner en el html
                idempleado = response.data.idEmpleado; //variable para usar aqui mismo en la funcion deleteAreadeconocimiento()
            });
            $scope.deleteAreadeconocimiento = function () {  
                $http.delete(areasdeconocimientoContext + '/' + idAreadeconocimiento, {}).then(function (response) {
                    $state.go('empleadoAreas', {empleadoId: idempleado }, {reload: true});
                });
            };
        }
    ]);
}
)(angular);