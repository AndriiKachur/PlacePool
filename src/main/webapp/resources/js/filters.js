'use strict';

/* Filters */
app.filter('placeInfo', function() {
	return function(input) {
		var result = 'Contact';
		if (input === 'IMAGE_URL') {
			result = 'Image';
		} else if (input === 'CONTACT') {
			result = 'Contact';
		}
		return result;
	};
});