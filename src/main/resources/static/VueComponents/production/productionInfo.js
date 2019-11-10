var productionInfo = new Vue({
    el: "#productionInfo",
    data: {
        uuid: window.location.pathname.split("/").pop(),
        Production: null, //new Production()
        loading: true,
    },
    created: function () {
        let vue = this;

        loadProductionGeneralInformation(vue);
    },
    methods: {
        getProductionStatusMessage(isActive) {
            return isActive ? 'Активна' : 'Неактивна';
        }
    }
});

function loadProductionGeneralInformation(vue) {
    makeServerCall('get', '/production/get-production-data/' + vue.uuid, null, (ResultData) => {
        vue.Production = ResultData;
        vue.loading = false;
    });
}