var productionInfo = new Vue({
    el: "#productionInfo",
    data: {
        uuid: window.location.pathname.split("/").pop(),
        loading: true,
    },
    created: function () {
        let vue = this;

        vue.loading = false;
    },
    methods: {

    }
});