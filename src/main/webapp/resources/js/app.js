'use strict';

/* App Module */

angular.module('app', ['appServices']).
  config(['$routeProvider', function($routeProvider) {
  $routeProvider
      .when('/', {templateUrl: 'resources/partials/place-list.html',   controller: PlaceListCtrl})
      .when('/place/:placeId', {templateUrl: 'resources/partials/place-detail.html', controller: PlaceDetailCtrl})
      .when('/createPlace', {templateUrl:'resources/partials/place-create.html', controller: PlaceCreateCtrl})
      .otherwise({redirectTo: '/'});
}]);