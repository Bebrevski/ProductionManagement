var allProductions = new Vue({
    el: "#allproductions",
    data: {
        productions: [],
        loading: true,
    },
    created: function () {
        let vue = this;

        loadAllProductions(vue);
    },
    methods: {}
});

function loadAllProductions(vue) {
    makeServerCall('get', '/production/get-all', null, (ResultData) => {
        vue.productions = ResultData;
        vue.loading = false;
    })
}