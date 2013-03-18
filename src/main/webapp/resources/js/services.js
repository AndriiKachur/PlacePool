'use strict';

/* Services */

angular.module('appServices', ['ngResource']).factory('Place', function($resource){
  return $resource('rest/places/:placeId', {}, {
    query: {method:'GET', isArray:true},
    save: {method: 'PUT', headers : {'Content-Type' : 'application/json'}}
  });
});