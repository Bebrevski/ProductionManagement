var materialComponent = Vue.component('material', {
    props: {
        funcdialog: Function,
        stockmaterials: Array,
    },
    data() {
        return {
            newMaterial: null,
            isAdded: false,

            materials: [],
            materialTypes: [],
            unitsOfMeasure: []
        }
    },
    created: function () {
        let vue = this;
        this.loadMaterials();

        loadMaterialTypesForComponent();
        loadUnitsOfMeasureForComponent();
    },
    methods: {
        onDialogClose: function () {
            this.funcdialog();
        },
        loadMaterials: function () {
            this.materials = this.stockmaterials;
        },
        addNewMaterial: function () {
            this.isAdded = true;
            this.newMaterial = new Material();
        }
    }
});

function loadMaterialTypesForComponent() {

}

function loadUnitsOfMeasureForComponent() {

}