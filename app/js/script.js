$(function(){
	$('.locationForm').submit(function(e){
		e.preventDefault();

		var $modal = $('#errorModal'),
			$modalBody = $('.modal-body'),
			city = $('#location').val();

		$('#location').val('');

		deleteMarkers();

		$.get('https://api.tamedia.cloud/homegate/v1/rs/real-estates?cht=purchall&nrs=10000&cit=' + city, {
			apikey: 'aac2c78bdfd7487d998f7ae679b55c9b'
		}).done(function(data) {

			console.log(data);

			if(data.resultCount == 0){
				$modalBody.html('<p>No data available, try another city.</p>');
				$modal.modal('show');
			}
			else{
				data.items.forEach(function(item, i){
					var location = item.geoLocation.split(","),
						advertisementId = data.advertisementId,
						bargainInt = 0;

					location = {lat: parseFloat(location[1]), lng:parseFloat(location[0])};

					if (item.price < 10000000 ){
						bargainInt = 1;
					}
					else if (item.price < 25000000 ){
						bargainInt = 0;
					}
					else if (item.price >= 25000000 ){
						bargainInt = -1;
					}

					addMarker(location, advertisementId, bargainInt);

					if (i == 0){
						centerMap(location);
					}
				});
			}
		}).fail(function(xhr, status, error) {
			console.log(xhr, status, error);
			var respError = $.parseJSON(xhr.responseText);
			$modalBody.html('<p>' + respError.message + '</p>');
			$modal.modal('show');
		});
	});
});

var map, markers = [];

function initMap(){
	map = new google.maps.Map(document.getElementById('map'), {
		zoom: 6,
		center: {lat: 47.3775499, lng: 8.4666751},
		mapTypeId: 'hybrid',

		//Custom controls position
		mapTypeControl: true,
		mapTypeControlOptions: {
			position: google.maps.ControlPosition.LEFT_TOP
		},
		zoomControl: true,
		zoomControlOptions: {
			position: google.maps.ControlPosition.LEFT_BOTTOM
		},
		scaleControl: true,
		streetViewControl: true,
		streetViewControlOptions: {
			position: google.maps.ControlPosition.LEFT_BOTTOM
		}
	});
}

// Adds a marker to the map and push to the array.
function addMarker(location, advertisementId, bargainInt) {
	var marker = new google.maps.Marker({
			position: location,
			animation: google.maps.Animation.DROP,
			map: map,
			advertisementId: advertisementId
	});

	if (bargainInt == 0){
		marker.setIcon('http://maps.google.com/mapfiles/ms/icons/blue-dot.png');
	}
	else if (bargainInt == -1){
		marker.setIcon('http://maps.google.com/mapfiles/ms/icons/green-dot.png');
	}
	markers.push(marker);
}

// Sets the map on all markers in the array.
function setMapOnAll(map) {
	for (var i = 0; i < markers.length; i++) {
		markers[i].setMap(map);
	}
}

// Removes the markers from the map, but keeps them in the array.
function clearMarkers() {
	setMapOnAll(null);
}

// Shows any markers currently in the array.
function showMarkers() {
	setMapOnAll(map);
}

// Deletes all markers in the array by removing references to them.
function deleteMarkers() {
	clearMarkers();
	markers = [];
}

function centerMap(location){
	map.setZoom(10);
	map.setCenter(location);
}