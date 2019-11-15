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
            newStock.inEditMode = true;
            getNewUuid().then((uuid) => newStock.uuid = uuid.data);
            this.stocks.unshift(newStock);
        },
    },
    computed: {
        getStockTypeName: function () {
            return stockTypeId => {
                let type = this.stockTypes.filter(x => x.key = stockTypeId);
                return type.length === 0 ? '-' : type[0].Value;
            }
        }
    }
});

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