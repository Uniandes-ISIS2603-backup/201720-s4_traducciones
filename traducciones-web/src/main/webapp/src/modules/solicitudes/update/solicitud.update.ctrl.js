(
        function (ng) {
            var mod = ng.module("solicitudModule");
            mod.constant("solicitudesContext", "api/solicitudes");
            mod.constant("areaContext", "api/areasdeconocimiento");
            mod.controller('solicitudUpdateCtrl', ['$scope', '$http', 'solicitudesContext', '$state', 'areaContext', '$rootScope', '$filter',
                function ($scope, $http, solicitudesContext, $state, areaContext, $rootScope, $filter) {
                    $rootScope.edit = true;

                    var idSolicitud = $state.params.solicitudId;

                    var idsAreas = [];

                    $scope.areasDetail = [];

                    
                    $http.get(solicitudesContext + '/' + idSolicitud).then(function (response) {
                        var solicitud = response.data;
                        $scope.solicitudDescripcion = solicitud.descripcion;
                        $scope.solicitudTipo = solicitud.tipo;
                        $scope.solicitudFechaInicio = new Date(solicitud.fechaInicio);
                        $scope.solicitudFechaEntrega = new Date(solicitud.fechaEntrega);
                        $scope.solicitudNumPalabras = solicitud.numPalabras;
                        $scope.areasSolicitud = solicitud.areasDeConocimiento;
                        $scope.mergeAreas($scope.areasSolicitud);
                    });
                  
                    $scope.mergeAreas = function (areas) {
                        for (var item in areas) {
                            idsAreas.push("" + areas[item].id);
                        }
                        $scope.getAreas(areas);
                    };

                    
                    $scope.getAreas = function (areas) {
                        $http.get(areaContext).then(function (response) {
                            $scope.AllAreas = response.data;
                            $scope.areasSol = areas;

                            var filteredAreas = $scope.AllAreas.filter(function (AllAreas) {
                                return $scope.areasSol.filter(function (areasSol) {
                                    return areasSol.id == AllAreas.id;
                                }).length == 0
                            });

                            $scope.areasDetail = filteredAreas;

                        });
                    };


                    //funciones para el drag and drop de HTML5 nativo
                    $scope.allowDrop = function (ev) {
                        ev.preventDefault();
                    };

                    $scope.drag = function (ev) {
                        ev.dataTransfer.setData("text", ev.target.id);
                    };

                    $scope.dropAdd = function (ev) {
                        ev.preventDefault();
                        var data = ev.dataTransfer.getData("text");
                        ev.target.appendChild(document.getElementById(data));
                        idsAreas.push("" + data);
                    };

                    $scope.dropDelete = function (ev) {
                        ev.preventDefault();
                        var data = ev.dataTransfer.getData("text");
                        ev.target.appendChild(document.getElementById(data));
                        var index = idsAreas.indexOf(data);
                        if (index > -1) {
                            idsAreas.splice(index, 1);
                        }
                    };

                    $scope.createSolicitud = function () {
                        $scope.createAreas();
                        $http.put(solicitudesContext + "/" + idSolicitud, {
                            descripcion : $scope.solicitudDescripcion,
                            tipo : $scope.solicitudTipo,
                            fechaInicio : $scope.solicitudFechaInicio,
                            fechaEntrega : $scope.solicitudFechaEntrega,
                            numPalabras : $scope.solicitudNumPalabras,
                            areasConocimiento : $scope.areasSolicitud
                        }).then(function (response) {
                            if (idsAreas.length >= 0) {
                                $http.put(solicitudesContext + "/" + response.data.id + "/areas", $scope.areasDetail).then(function (response) {
                                });
                            }
                            $state.go('clientesList', {solicitudId: response.data.id}, {reload: true});
                        });
                    };

                    $scope.createAreas = function () {
                        $scope.areasDetail = [];
                        for (var ite in idsAreas) {
                            for (var all in $scope.AllAreas) {
                                if ($scope.AllAreas[all].id === parseInt(idsAreas[ite])) {
                                    $scope.areasDetail.push($scope.AllAreas[all]);
                                }
                            }
                        }
                    };
                }
            ]);
        }
)(window.angular);

