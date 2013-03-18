'use strict';

/* Controllers */

function PlaceListCtrl($scope, Place) {
  $scope.places = Place.query();

}


function PlaceDetailCtrl($scope, $routeParams, Place) {
  $scope.place = Place.get({placeId: $routeParams.placeId}, function(place) {
    
  });

  $scope.setImage = function(imageUrl) {
    $scope.mainImageUrl = imageUrl;
  }
}

function PlaceCreateCtrl($scope, Place, $location) {
	$scope.place = {};
	
	$scope.save = function() {
		$.ajax({
			url: 'rest/places/',
			type: 'PUT',
			dataType: 'json',
			contentType: 'application/json',
			data: $scope.place,
			success: function(data, textStatus, jqXHR) {
				$location.path('/place/' + data.id);
			}
		});
	}
}