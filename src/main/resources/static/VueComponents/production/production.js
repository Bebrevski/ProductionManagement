var productionComponent = new Vue({
    el: "#production",
    data: {
        Production: new Production(), //change to null when logic for edit is needed
        loading: false,
        isGPSCoordinates: true
    },
    created: function () {
        let vue = this;

        getNewUuid().then((res) => {vue.Production.uuid = res.data}) ; // will be changed when we have create and edit form
    },
    methods: {
        onProductionSubmit() {
            handleProductionSubmit(this);
        }
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

function handleProductionSubmit(vue) {
    vue.Production.dateCreated = dateFormated(Date.now());
    console.log(vue.Production)
    makeServerCall('post', '/production/submit', vue.Production, (ResultData) => {
       console.log(ResultData);
    });
}