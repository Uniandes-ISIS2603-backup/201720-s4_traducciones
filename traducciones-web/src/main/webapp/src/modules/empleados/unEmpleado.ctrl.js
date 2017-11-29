/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module("empleadoModule");
    mod.constant("empleadoContext", "api/empleados");
    mod.controller('unEmpleadoCtrl', ['$scope', '$http', 'empleadosContext', '$state',
        function ($scope, $http, empleadosContext, $state) {
            
            var idEmpleado = $state.params.empleadoId;
            
            if ($state.params.empleadoId !== undefined) {
                $http.get(empleadosContext + '/' + idEmpleado).then(function (response) {
                    $scope.empleado = response.data;
                    $scope.areas = response.data.areasdeconocimiento;
                    
                });
            }
        }
        
    ]);
}
)(angular);

