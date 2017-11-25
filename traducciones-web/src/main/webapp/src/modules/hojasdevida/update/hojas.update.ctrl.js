(function (ng) {
    var mod = ng.module("hojaModule");
  
    mod.controller('hojasUpdateCtrl', ['$scope', '$http', '$state',
        function ($scope, $http, $state) {
            var idHoja=$state.params.hojaId;
            var idEmpleado=$state.params.empleadoId;
            $scope.idHoja = idHoja;
            $scope.idEmpleado = idEmpleado;
           
            $http.get("api/empleados/"+idEmpleado+"/hojadevida").then(function (response) {
                   
            $scope.idHojaDeVida = response.data.id;
                $scope.hojaName = response.data.name;
                $scope.hojaDescripcion = response.data.descripcion;
                $scope.hojaFormacionAcademica = response.data.formacionAcademica;
                $scope.hojaPerfilProfesional = response.data.perfilProfesional;
                 $http.get("api/hojadevida/"+idHoja).then(function (response2)
                {                  
                $scope.trayectoriasHoja=response2.data.trayectorias;
                $scope.idiomasHoja=response2.data.idiomas;
                });
                    
                });
            
            $scope.updateHoja = function () {
                $http.put("api/empleados/"+idEmpleado + '/hojadevida',{
                    id:$scope.idHojaDeVida,
                    name: $scope.hojaName,                    
                    descripcion: $scope.hojaDescripcion,
                    perfilProfesional: $scope.hojaPerfilProfesional,
                    formacionAcademica: $scope.hojaFormacionAcademica,
                    trayectorias:$scope.trayectoriasHoja,
                    idiomas:$scope.idiomasHoja
                }).then(function () {
                    $state.go('empleadoHojas',({empleadoId:idEmpleado,empleadoHoja:idHoja}),  {reload: true});             
                    
                });
            }}    
        
    ]);
}
)(angular);