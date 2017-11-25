/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module("empleadoModule");
    mod.constant("empleadosContext", "api/empleados");
    mod.controller('propuestasDetailCtrl', ['$scope', '$http', 'empleadosContext', '$state',
        function ($scope, $http, empleadosContext, $state) {
            
            var idEmpleado = $state.params.empleadoId;
            
            $http.get(empleadosContext + '/' + idEmpleado + "/propuestas").then(function (response) {
                $scope.prueba = idEmpleado;              
                $scope.propuestas = response.data;
            });
        }
    ]);
}
)(angular);

