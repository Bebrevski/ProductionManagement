var stockComponent = Vue.component('stock', {
    data() {
        return {
            productionUuid: window.location.pathname.split("/").pop(),

            stocks: [],
            stockTypes: [],
            materialTypes: [],
            unitsOfMeasure: [],

            objectBackups: {},

            loadedData: [],
            stockDataIsLoaded: true,

            headers: [
                { text: 'Материал', align: 'left', value: 'materialType.name', width: "20%" },
                { text: 'Код', value: 'code', width: "20%" },
                { text: 'Налично количество', value: 'quantity', width: "20%" },
                { text: 'Минимално кол.', value: 'minimumQuantity', width: "20%" },
                { text: 'Последно заприходени', value: 'receivedOn', width: "20%" },
            ],
            pagination: {},
            search: '',
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
        },
        onEditStock(stock, index) {
            this.objectBackups[stock.uuid] = copyObject(stock);
            this.stocks[index].inCreateMode = true;
            this.$forceUpdate();
        },
        onDeleteStock(index) {
            let vue = this;
            handleDeleteStock(vue, index);
        },
        onPreviewMaterials(stock) {
            stock.inPreviewMode = !stock.inPreviewMode;
            this.$forceUpdate();
        },
        onEditMaterials(stock) {
            stock.inEditMaterialsMode = true;
            this.$forceUpdate();
        }
    },
    computed: {
        formatDate: function() {
            return date => {
                return dateFormatter(date);
            }
        }
    }
});

function handleSaveStock(stock, vue) {
    makeServerCall('post', '/stock/submitStock/' + vue.productionUuid, stock, (ResultData) => {
        stock = Object.assign(stock, ResultData);
        stock.inCreateMode = false;
        vue.$forceUpdate();
    });
}

function handleDeleteStock(vue, index) {
    if (vue.stocks[index].id !== 0) {
        promptActionConfirmation(questionToBeDeleted, () => {
           let stockToBeDeleted = vue.stocks[index];

           if (stockToBeDeleted.id !== 0) {
               makeServerCall('post', '/stock/deleteStock/' + vue.productionUuid, stockToBeDeleted, () => {
                  vue.stocks.splice(index, 1);
               });
           }
        });
    }else {
        vue.stocks.splice(index, 1);
    }
}

function loadStocks(vue) {
    makeServerCall('get', '/stock/getStocks/' + vue.productionUuid, null, (ResultData) => {
        vue.stocks = ResultData;
        console.log(vue.stocks);
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