'use strict';

/* Filters */

//if param type exists then check for type, print user friendly type otherwise
app.filter('placeInfo', function() {
	return function(input, type) {
		if (angular.isUndefined(type)) {
			var result = 'Contact';
			if (input === 'IMAGE_URL') {
				result = 'Image';
			} else if (input === 'DESC_URL') {
				result = 'Description';
			}
			return result;
		} else {
			var result = [];
			if (angular.isArray(input)) {
				for (var i in input) {
					if (input[i].type === type) {
						result.push(input[i]);
					}
				}
			}
			return result;
		}
	};
});