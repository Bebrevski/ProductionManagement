var map;

function initMapCoordinates() {
    var latitude = 42.6371508;
    var longitude = 25.7459637;

    var myLatLng = {lat: latitude, lng: longitude};

    map = new google.maps.Map(document.getElementById('mapCoordinates'), {
        center: myLatLng,
        zoom: 7,
        disableDoubleClickZoom: true
    });

    // Update lat/long value of div when anywhere in the map is clicked
    google.maps.event.addListener(map, 'click', function (event) {
        document.getElementById('latclicked').innerHTML = event.latLng.lat();
        document.getElementById('longclicked').innerHTML = event.latLng.lng();
    });

    var marker = new google.maps.Marker({
        position: myLatLng,
        map: map,

        // setting latitude & longitude as title of the marker
        // title is shown when you hover over the marker
        title: latitude + ', ' + longitude
    });

    // Update lat/long value of div when the marker is clicked
    marker.addListener('click', function (event) {
        document.getElementById('latclicked').innerHTML = event.latLng.lat();
        document.getElementById('longclicked').innerHTML = event.latLng.lng();
    });

    // Create new marker on double click event on the map
    google.maps.event.addListener(map, 'dblclick', function (event) {
        var marker = new google.maps.Marker({
            position: event.latLng,
            map: map,
            title: event.latLng.lat() + ', ' + event.latLng.lng()
        });

        // Update lat/long value of div when the marker is clicked
        marker.addListener('click', function () {
            document.getElementById('latclicked').innerHTML = event.latLng.lat();
            document.getElementById('longclicked').innerHTML = event.latLng.lng();
        });
    });
}