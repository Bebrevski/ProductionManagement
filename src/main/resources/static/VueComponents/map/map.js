// Initialize and add the map
function initMap() {
    let bulgaria = {lat: 42.6371508, lng: 25.7459637}; // location of Bulgaria
    let map = new google.maps.Map(document.getElementById('map'), {
        zoom: 8,
        center: bulgaria
    });
}