'use strict';

/* Controllers */

function PlaceListCtrl($scope, $rootScope, Place, UnpublishedPlace) {
	sessionStorage.published = $scope.published = sessionStorage.published === undefined ? 'true' : sessionStorage.published;
	$scope.$watch('published', function(newValue, oldValue) {
		sessionStorage.published = newValue;
		if (newValue === 'true') {
			$scope.places = Place.query();
		} else {
			$scope.places = UnpublishedPlace.query();
		}
	});
}


function PlaceDetailCtrl($scope, $rootScope, $routeParams, Place) {
	$scope.images = [];
	$scope.info = [];
	
	Place.get({placeId: $routeParams.placeId}, function(place) {
		$scope.place = place;
		for (var i in place.info) {
			if (place.info[i].type === 'IMAGE_URL') {
				$scope.images.push(place.info[i]);
			} else {
				$scope.info.push(place.info[i]);
			}
		}
	});
}

function PlaceDetailUnpublishedCtrl($scope, $rootScope, $routeParams, UnpublishedPlace) {
	UnpublishedPlace.get({placeId: $routeParams.placeId}, function(place) {
		$scope.place = place;
	});
}

function PlaceCreateCtrl($scope, Place, $location) {
	$scope.linkTypes = ['CONTACT', 'IMAGE_URL', 'DESC_URL'];
	$scope.place = {};
	$scope.place.info = [];
	
	$scope.save = function() {
		Place.save($scope.place, function(data){
			$location.path('/place/new/' + data.id);
		});
	};
	
	$scope.addLink = function() {
		$scope.place.info.push({});
	};
}