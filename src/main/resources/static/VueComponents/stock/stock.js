var stockComponent = Vue.component('stock', {
    data() {
        return {
            productionUuid: window.location.pathname.split("/").pop(),

            stocks: [],
            stockTypes: [],
            materialTypes: [],
            unitsOfMeasure: [],

            loadedData: [],
            stockDataIsLoaded: true,
        }
    },
    created: function () {
        let vue = this;

        loadStocks(vue);
        loadStockTypes(vue);
        loadMaterialTypes(vue);
        loadUnitsOfMeasure(vue);
    },
    methods: {
        onCreateStock() {
            let newStock = new Stock();
            newStock.inCreateMode = true;
            newStock.lastModified = dateFormatted(new Date());
            getNewUuid().then((uuid) => newStock.uuid = uuid.data);
            this.stocks.unshift(newStock);
        },
        onSaveStock(newStock) {
            let vue = this;
            handleSaveStock(newStock, vue);
        }
    },
    computed: {
    }
});

function handleSaveStock(newStock, vue) {
    makeServerCall('post', '/stock/submitStock/' + vue.productionUuid, newStock, (ResultData) => {
        console.log(ResultData);
    });
}

function loadStocks(vue) {
    makeServerCall('get', '/stock/getStocks/' + vue.productionUuid, null, (ResultData) => {
        vue.stocks = ResultData;
    });
}

//Nomenclatures
function loadStockTypes(vue) {
    makeServerCall('get', '/stock/getStockTypes', null, (ResultData) => {
        vue.stockTypes = ResultData;
    });
}

function loadMaterialTypes(vue) {
    makeServerCall('get', '/stock/getMaterialTypes', null, (ResultData) => {
        vue.materialTypes = ResultData;
    });
}

function loadUnitsOfMeasure(vue) {
    makeServerCall('get', '/stock/getUnitsOfMeasure', null, (ResultData) => {
        vue.unitsOfMeasure = ResultData;
    });
}

//End on nomenclatures