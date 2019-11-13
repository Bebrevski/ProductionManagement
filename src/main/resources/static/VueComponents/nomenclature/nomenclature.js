var dropdownComponent = Vue.component('nomenclature', {
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
            newNomenclatureItem.InEditMode = true;
            getNewGuid().then((guid) => newNomenclatureItem.GUID = guid.data);
            this.nomenclatureRows.unshift(newNomenclatureItem);
        },
        onEditNomenclature: function (nomenclatureItem) {
            this.objectBackups[nomenclatureItem.GUID] = copyObject(nomenclatureItem);
            nomenclatureItem.InEditMode = true;
            this.$forceUpdate();
        },
        onCancelEditNomenclature: function (index) {
            const nGuid = this.nomenclatureRows[index].GUID;
            this.nomenclatureRows[index].InEditMode = false;

            if (this.objectBackups[nGuid] === undefined) {
                this.nomenclatureRows.splice(index, 1);
            } else {
                for (let prop in this.objectBackups[nGuid]) {
                    this.nomenclatureRows[index][prop] = this.objectBackups[nGuid][prop];
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
        },
    },
    computed: {
        getUniqueKey: function () {
            return Math.random().toString();
        }
    }
});

function getDataForNomenclature(vue, nomenclatureID) {
    console.log(nomenclatureID);
    makeServerCall('get', '/nomenclature/getNomenclatureItems/' + nomenclatureID, null, (ResultData) => {
        let temp = [];

        ResultData.forEach(function (x) {
            temp.unshift(Object.assign(new Nomenclature(), x));
        });

        vue.nomenclatureRows = temp;

        setTimeout(() => { vue.loading = false; }, 1000);
    });
}

function handleSave(vue, nomenclatureItem, index) {
    nomenclatureItem.NomenclatureID = vue.id;

    makeServerCall('post', '/Nomenclature/SubmitNomenclature', nomenclatureItem, (ResultData) => {
        let savedNomenclatureItem = ResultData;

        nomenclatureItem.uuid = savedNomenclatureItem.uuid;
        vue.nomenclatureRows[index].isActive = savedNomenclatureItem.isActive;
        nomenclatureItem.inEditMode = false;
    });
}

function handleRemoveNomenclature(vue, index) {
    promptActionConfirmation(questionToBeDeleted, () => {

        let nomenclatureItemToBeRemoved = vue.nomenclatureRows[index];
        nomenclatureItemToBeRemoved.nomenclatureID = vue.id;

        if (nomenclatureItemToBeRemoved.GUID !== '') {
            makeServerCall('post', '/nomenclature/removeNomenclature', nomenclatureItemToBeRemoved, (ResultData) => {
                vue.nomenclatureRows.splice(index, 1);
            });
        }
    })
}