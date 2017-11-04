(function (ng) {
    var mod = ng.module("empleadoModule");
    mod.constant("empleadosContext", "api/empleados");
    mod.controller('hojasDetailedCtrl', ['$scope', '$http', 'empleadosContext', '$state',
        function ($scope, $http, empleadosContext, $state) {
            var idEmpleado = $state.params.empleadoId;
           
           
            $http.get(empleadosContext + '/' + idEmpleado).then(function(response){
                $scope.nombreEmpleado = response.data.name;
               $scope.idEmpleado = response.data.id;
            });
            
            $http.get(empleadosContext + '/' + idEmpleado + "/hojadevida").then(function (response) {
                
                    idHoja=response.data.id;
                    $http.get("api/hojadevida/"+idHoja).then(function (response2) {
                $scope.currentHoja = response2.data;
                
                
            });
                
            });
        }
    ]);
}
)(angular);