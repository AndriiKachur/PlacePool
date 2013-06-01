'use strict';

/* App Module */

var app = angular.module('app', ['appServices', 'ui', 'ngSanitize']).
  config(['$routeProvider', function($routeProvider) {
  $routeProvider
      .when('/', {templateUrl: 'resources/partials/place-list.html',   controller: PlaceListCtrl})
      .when('/place/:placeId', {templateUrl: 'resources/partials/place-detail.html', controller: PlaceDetailCtrl})
      .when('/place/new/:placeId', {templateUrl: 'resources/partials/place-detail.html', controller: PlaceDetailUnpublishedCtrl})
      .when('/createPlace', {templateUrl:'resources/partials/place-create.html', controller: PlaceCreateCtrl})
      .otherwise({redirectTo: '/'});
}]);

app.value('ui.config', {
	select2 : {
		allowClear : true
	},
	tinymce : {
		width : "640",
		height: "230"
	}
});