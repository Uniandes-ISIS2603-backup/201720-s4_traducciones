(function (ng) {
    var mod = ng.module("empleadoModule");
    mod.constant("empleadosContext", "api/empleados");
    mod.controller('empleadoUpdateCtrl', ['$scope', '$http', 'empleadosContext', '$state',
        function ($scope, $http, empleadosContext, $state) {
            var idEmpleado = $state.params.empleadoId;
            $http.get(empleadosContext + '/' + idEmpleado ).then(function (response) {
                $scope.empleadoName = response.data.name;
                $scope.empleadoTipo = response.data.tipo;
            });
            $scope.updateEmpleado = function(){
            $http.put(empleadosContext + '/' + idEmpleado, {
                name: $scope.empleadoName,
                tipo: $scope.empleadoTipo
            }).then(function (response) {
                //empleado updated successfully
                $state.go('empleadosList', {empleadoId: response.data.id}, {reload: true});
            });
        };
            var temp = $scope.empleadoTipo;
            var mySelect = document.getElementById('tipo');
            for(var i = 0; i < mySelect.options.length; i++) {
            if(i.value == temp) {
            mySelect.selectedIndex = i;
            break;
  }
    }}
]);
}
    )(angular);