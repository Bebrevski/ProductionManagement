var productionComponent = new Vue({
    el: "#production",
    data: {
        Production: null, //Production model
        loading: true,
        isGPSCoordinates: true,
        inEditModel: false,
        page: window.location.pathname.split("/")[2].toUpperCase() // CREATE/EDIT
    },
    created: function () {
        let vue = this;

        if (vue.page === 'CREATE') {
            vue.Production = new Production();

            getNewUuid().then((res) => {
                vue.Production.uuid = res.data;
                this.loading = false;
            });

        } else { //EDIT
            let productionUuid = window.location.pathname.split("/").pop();
            makeServerCall('get', '/production/get-production-data/' + productionUuid, null, (ResultData) => {
                vue.Production = Object.assign(new Production(), ResultData);
                vue.inEditModel = true;
                vue.loading = false;
            });
        }
    },
    methods: {
        onProductionSubmit() {
            handleProductionSubmit(this);
        },
        redirectToAllProductions() {
            const link = document.createElement('a');
            link.href = '/production/show-all';
            link.click();
        }
    },
    computed: {
        getDate: function () {
            return prodCreatedDate => {
                if (prodCreatedDate) {
                    return dateFormatted(prodCreatedDate);
                }
                return dateFormatted(Date.now());
            }
        }
    }
});

function handleProductionSubmit(vue) {
    vue.Production.dateCreated = dateFormatted(Date.now());
    makeServerCall('post', '/production/submit', vue.Production, (ResultData) => {
        const link = document.createElement('a');
        link.href = '/production/show-all';
        link.click();
    });
}