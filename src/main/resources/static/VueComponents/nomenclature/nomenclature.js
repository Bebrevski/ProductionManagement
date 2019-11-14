var dropdownComponent = Vue.component('nomenclature', {
    template: '#movie-template',
    props: {
        id: Number
    },
    data() {
        return {
            dataIsLoaded: false,
            nomenclatureRows: [],
            loading: true,
            objectBackups: {}
        };
    },
    created: function () {
        let vue = this;
        this.loadDataForNomenclature(vue);
    },
    methods: {
        loadDataForNomenclature: function (vue) {
            getDataForNomenclature(vue, vue.id);
        },
        onCreateNomenclature: function () {
            let newNomenclatureItem = new Nomenclature();
            newNomenclatureItem.inEditMode = true;
            getNewUuid().then((uuid) => newNomenclatureItem.uuid = uuid.data);
            this.nomenclatureRows.unshift(newNomenclatureItem);
        },
        onEditNomenclature: function (nomenclatureItem) {
            this.objectBackups[nomenclatureItem.uuid] = copyObject(nomenclatureItem);
            nomenclatureItem.inEditMode = true;
            this.$forceUpdate();
        },
        onCancelEditNomenclature: function (index) {
            const nUuid= this.nomenclatureRows[index].uuid;
            this.nomenclatureRows[index].inEditMode = false;

            if (this.objectBackups[nUuid] === undefined) {
                this.nomenclatureRows.splice(index, 1);
            } else {
                for (let prop in this.objectBackups[nUuid]) {
                    this.nomenclatureRows[index][prop] = this.objectBackups[nUuid][prop];
                }
            }
        },
        onSaveNomenclature: function (nomenclatureItem, index) {
            let vue = this;
            handleSave(vue, nomenclatureItem, index);
        },
        onRemoveNomenclature: function (index) {
            let vue = this;
            handleRemoveNomenclature(vue, index);
        }
    }
});

function getDataForNomenclature(vue, nomenclatureID) {
    makeServerCall('get', '/nomenclature/getNomenclatureItems/' + nomenclatureID, null, (ResultData) => {
        let temp = [];

        ResultData.forEach(function (x) {
            temp.unshift(Object.assign(new Nomenclature(), x));
        });

        vue.nomenclatureRows = temp;

        vue.dataIsLoaded = true;
    });
}

function handleSave(vue, nomenclatureItem, index) {
    nomenclatureItem.nomenclatureID = vue.id;

    makeServerCall('post', '/nomenclature/submitNomenclature', nomenclatureItem, (ResultData) => {
        let savedNomenclatureItem = ResultData;

        nomenclatureItem.uuid = savedNomenclatureItem.uuid;
        vue.nomenclatureRows[index].active = savedNomenclatureItem.active;
        nomenclatureItem.inEditMode = false;
    });
}

function handleRemoveNomenclature(vue, index) {
    promptActionConfirmation(questionToBeDeleted, () => {

        let nomenclatureItemToBeRemoved = vue.nomenclatureRows[index];
        nomenclatureItemToBeRemoved.nomenclatureID = vue.id;

        if (nomenclatureItemToBeRemoved.uuid !== '') {
            makeServerCall('post', '/nomenclature/removeNomenclature', nomenclatureItemToBeRemoved, (ResultData) => {
                vue.nomenclatureRows.splice(index, 1);
            });
        }
    })
}