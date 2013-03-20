'use strict';

/* Services */

var appModules = angular.module('appServices', ['ngResource']);

appModules.factory('Place', function($resource){
  return $resource('rest/places/:placeId', {}, {
    query: {method:'GET', isArray:true},
    save: {method: 'PUT', headers : {'Content-Type' : 'application/json'}}
  });
});

appModules.factory('UnpublishedPlace', function($resource){
	  return $resource('rest/places/new/:placeId', {}, {
	    query: {method:'GET', isArray:true}
	  });
	});