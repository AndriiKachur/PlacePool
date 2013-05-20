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
	$scope.place = Place.get({placeId: $routeParams.placeId}, function(place) { });

	$scope.setImage = function(imageUrl) {
		$scope.mainImageUrl = imageUrl;
	};
}

function PlaceDetailUnpublishedCtrl($scope, $rootScope, $routeParams, UnpublishedPlace) {
	$scope.place = UnpublishedPlace.get({placeId: $routeParams.placeId}, function(place) { });

	$scope.setImage = function(imageUrl) {
		$scope.mainImageUrl = imageUrl;
	};
}

function PlaceCreateCtrl($scope, Place, $location) {
	$scope.place = {};
	$scope.place.info = [];
	
	//TODO: Angular don't support 'application/json' content type?
	$scope.save = function() {
		$.ajax({
			url: 'rest/places/',
			type: 'PUT',
			dataType: 'json',
			contentType: 'application/json',
			data: angular.toJson($scope.place),
			success: function(data, textStatus, jqXHR) {
				$location.path('/place/new/' + data.id);
				$scope.$apply();
			}
		});
	};
	
	$scope.addLink = function() {
		$scope.place.info.push({});
	};
}