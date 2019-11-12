var nomenclatureTabs = new Vue({
    el: '#nomenclaturetabs',
    data: {
        tab: null,
        nomenclatureHeaders: [],
        loading: true
    },
    created: function () {
        let vue = this;
        getNomenclatureHeaders(vue);
    },
    methods: {}
});

function getNomenclatureHeaders(vue) {
    makeServerCall('get', '/nomenclature/getNomenclatureHeaders', null, (ResultData) => {
        console.log(ResultData);
        vue.nomenclatureHeaders = ResultData;
        vue.loading = false;
    });
}