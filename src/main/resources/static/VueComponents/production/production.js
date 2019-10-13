var productionComponent = new Vue({
    el: "#production",
    data: {
        Production: new Production(), //change to null when logic for edit is needed
        loading: false,
        isGPSCoordinates: true
    },
    created: function () {

    },
    methods: {

    },
    computed: {
        getDate: function () {
            return prodCreatedDate => {
                if (prodCreatedDate) {
                    return dateAndTimeFormated(prodCreatedDate);
                }
                return dateFormated(Date.now());
            }
        }
    }
});