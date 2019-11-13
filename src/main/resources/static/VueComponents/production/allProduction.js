var allProductions = new Vue({
    el: "#allproductions",
    data: {
        productions: [],
        headers: [
            {text: 'Име на база', sortable: true, align: 'left', value: 'name', width: "9%"},
            {text: 'Идентификационен номер', sortable: false, value: 'identifyingNumber', width: "10%"},
            {text: 'Мейл на базата', sortable: false, value: 'email', width: "15%"},
            {text: 'Телефон', sortable: false, value: 'phone', width: "10%"},
            {text: 'Адрес', sortable: false, value: 'fullAddress', width: "14%"},
            {text: 'GPS координати', sortable: false, value: 'GPSCoordinates', width: "14%"},
            {text: 'Въведено на', sortable: false, value: 'dateCreated', width: "8%"},
            {text: 'Описание', sortable: false, value: 'description', width: "15%"},
            {text: 'Действия', sortable: false, value: null, width: '5%'}
        ],

        loading: true,
    },
    created: function () {
        let vue = this;

        loadAllProductions(vue);
    },
    methods: {
        editItem(item) {
            const link = document.createElement('a');
            link.href = '/production/edit/' + item.uuid;
            link.click();
        },
        redirectToProduction(item) {
            const link = document.createElement('a');
            link.href = '/production/view/' + item.uuid;
            link.target = '_blank';
            link.click();
        }
    }
});

function loadAllProductions(vue) {
    makeServerCall('get', '/production/get-all', null, (ResultData) => {
        vue.productions = ResultData;
        vue.loading = false;
    })
}