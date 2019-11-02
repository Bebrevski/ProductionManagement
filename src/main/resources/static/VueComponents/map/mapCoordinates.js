var map;

function initMapCoordinates() {
    let latitude = 42.6371508;
    let longitude = 25.7459637;

    var myLatLng = {lat: latitude, lng: longitude};

    map = new google.maps.Map(document.getElementById('mapCoordinates'), {
        center: myLatLng,
        zoom: 7,
        disableDoubleClickZoom: true
    });

    // Create new marker on double click event on the map
    google.maps.event.addListener(map, 'click', function (event) {

        productionComponent.Production.GPSCoordinates = `${event.latLng.lat()}, ${event.latLng.lng()}`;
        myLatLng = {lat: event.latLng.lat(), lng: event.latLng.lng()};

        let marker = new google.maps.Marker({
            position: myLatLng,
            map: map
        });
    });
}