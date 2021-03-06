$(function(){
	$('.locationForm').submit(function(e){
		e.preventDefault();

		var $modal = $('#errorModal'),
			$modalBody = $('.modal-body'),
			city = $('#location').val();

		$('#location').val('');

		deleteMarkers();

		$.get('http://localhost:9000/search?city=' + city+'&toBuy=true')
		.done(function(data) {
			if(data.resultCount == 0){
				$modalBody.html('<p>No data available, try another city.</p>');
				$modal.modal('show');
			}
			else{
				data.list.forEach(function(item, i){
					var location = item.geoLocation.split(","),
						advertisementId = item.advId,
						bargainInt = 0;

					location = {lat: parseFloat(location[1]), lng:parseFloat(location[0])};

					if (item.fairPrice >= item.actualPrice ){
						bargainInt = 1;
					}
					else if (Math.abs(item.fairPrice-item.actualPrice)<50000){
						bargainInt = 0;
					}
					else {
						bargainInt = -1;
					}

					addMarker(location, advertisementId, bargainInt);

					if (i == 0){
						centerMap(location);
					}
				});

				$('#legend').show();
			}
		}).fail(function(xhr, status, error) {
			var respError = $.parseJSON(xhr.responseText);
			$modalBody.html('<p>' + respError.message + '</p>');
			$modal.modal('show');
		});
	});
});

var map, markers = [];

function initMap(){
	map = new google.maps.Map(document.getElementById('map'), {
		zoom: 7,
		center: {lat: 46.8095942, lng: 7.103087},
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

	var iconBase = 'http://maps.google.com/mapfiles/ms/icons/';
    var icons = {
      parking: {
        name: 'Cheap',
        icon: iconBase + 'green-dot.png'
      },
      library: {
        name: 'Fair',
        icon: iconBase + 'blue-dot.png'
      },
      info: {
        name: 'Expensive',
        icon: iconBase + 'red-dot.png'
      }
    };

    var legend = document.getElementById('legend');
        for (var key in icons) {
          var type = icons[key];
          var name = type.name;
          var icon = type.icon;
          var div = document.createElement('div');
          div.innerHTML = '<img src="' + icon + '"> ' + name;
          legend.appendChild(div);
        }

        map.controls[google.maps.ControlPosition.RIGHT_CENTER].push(legend);
}

// Adds a marker to the map and push to the array.
function addMarker(location, advertisementId, bargainInt) {
	var marker = new google.maps.Marker({
			position: location,
			animation: google.maps.Animation.DROP,
			map: map,
			url: 'http://www.homegate.ch/buy/'+advertisementId,
			advertisementId: advertisementId
	});

	google.maps.event.addListener(marker, 'click', function() {
		window.open(marker.url,'_blank');
    });

	if (bargainInt == -1){
		marker.setIcon('http://maps.google.com/mapfiles/ms/icons/red-dot.png');
	}
	else if (bargainInt == 0){
		marker.setIcon('http://maps.google.com/mapfiles/ms/icons/blue-dot.png');
	}
	else if (bargainInt == 1){
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