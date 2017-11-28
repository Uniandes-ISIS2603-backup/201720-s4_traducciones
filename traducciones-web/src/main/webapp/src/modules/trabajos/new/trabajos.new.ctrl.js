(function (ng) {
    var mod = ng.module("trabajoModule");
    mod.constant("trabajosContext", "api/trabajos");
    mod.controller('trabajosNewCtrl', ['$scope', '$http', 'trabajosContext', '$state', '$rootScope',
        function ($scope, $http, trabajosContext, $state, $rootScope) {
            $rootScope.edit = false;


            $scope.data = {
                group1: 'Banana',
                group2: '2',
                group3: 'avatar-1'
            };

            $scope.radioData = [
                {label: '1', value: 1},
                {label: '2', value: 2},
                {label: '3', value: '3', isDisabled: true},
                {label: '4', value: '4'}
            ];

            $scope.createTrabajo = function () {
                $http.post(trabajosContext, {
                    name: $scope.trabajoName,
                    terminado: $scope.trabajoTerminado
                }).then(function (response) {
                    //Author created successfully
                    $state.go('trabajosList', {trabajoId: response.data.id}, {reload: true});
                });
            };

            $scope.addCalificacion = function () {
                $http.post(trabajosContext, {
                    name: $scope.calificacionName,
                    id: $scope.calificacionId,
                    valor: $scope.calificacionValor,
                    comentario: $scope.calificacionComentario
                }).then(function (response) {

                    $state.go('trabajosList', {trabajoId: response.data.id}, {reload: true});
                });
            };


        }
    ]);
}
)(angular);