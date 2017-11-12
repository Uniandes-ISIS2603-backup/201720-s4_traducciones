(function (ng) {
    var mod = ng.module("trabajoModule");
    mod.constant("trabajoContext", "api/trabajos");
    mod.controller('trabajosUpdateCtrl', ['$scope', '$http', 'trabajoContext', '$state',
        function ($scope, $http, trabajoContext, $state) {
            var idtrabajo=$state.params.trabajoId;
            
            $http.get(trabajoContext + '/' + idtrabajo).then(function (response) {
                    
            $scope.trabajoName = response.data.name;
                $scope.trabajoTerminado = response.data.terminado;
                
                    
                });
            
            $scope.updateTrabajo = function () {
                $http.put(trabajoContext + '/' + idtrabajo,{
                     name: $scope.trabajoName,
                    terminado: $scope.trabajoTerminado
                }).then(function () {
                    $state.go('trabajosList',  {reload: true});             
                    
                });
            }}
            
          
            
            
            
            
        
    ]);
}
)(angular);