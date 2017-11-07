(function (ng) {
    var mod = ng.module("hojaModule");
    mod.constant("hojaContext", "api/hojadevida");
    mod.controller('hojasUpdateCtrl', ['$scope', '$http', 'hojaContext', '$state',
        function ($scope, $http, hojaContext, $state) {
            var idHoja=$state.params.hojaId;
            
            $http.get(hojaContext + '/' + idHoja).then(function (response) {
                    
            $scope.idHojaDeVida = response.data.id;
                $scope.hojaName = response.data.name;
                $scope.hojaDescripcion = response.data.descripcion;
                $scope.hojaFormacionAcademica = response.data.formacionAcademica;
                $scope.hojaPerfilProfesional = response.data.perfilProfesional;
                
                    
                });
            
            $scope.updateHoja = function () {
                $http.put(hojaContext + '/' + idHoja,{
                    name: $scope.hojaName,                    
                    descripcion: $scope.hojaDescripcion,
                    perfilProfesional: $scope.hojaPerfilProfesional,
                    formacionAcademica: $scope.hojaFormacionAcademica
                }).then(function (response) {
                    $state.go('hojasList',  {reload: true});             
                    
                });
            }}    
        
    ]);
}
)(angular);