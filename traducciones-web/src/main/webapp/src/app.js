(function (ng) {
    var app = angular.module('mainApp', [
        // External dependencies
        'ui.router',
       'ui.bootstrap',
        // Internal modules dependencies
        'clienteModule',
        'pagoModule',
        'tarjetaModule',
        'trabajoModule',
        'hojaModule',
        'ofertasModule',
        'empleadoModule',
        'areadeconocimientoModule',
        'idiomaModule',
        'propuestasModule',
        'solicitudModule'
        

    ]);
    // Resuelve problemas de las promesas
    app.config(['$qProvider', function ($qProvider) {
            $qProvider.errorOnUnhandledRejections(false);
        }]);
})(window.angular);



