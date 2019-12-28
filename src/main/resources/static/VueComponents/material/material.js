var materialComponent = Vue.component('material', {
    props: {
        funcdialog: Function,
        stockmaterials: Array,
    },
    data() {
        return {
            materials: [],
        }
    },
    created: function () {
        let vue = this;
        this.loadMaterials();
    },
    methods: {
        onDialogClose: function () {
            this.funcdialog();
        },
        loadMaterials: function () {
            this.materials = this.stockmaterials;
            console.log(this.materials);
        },
        addNewMaterial: function () {
            console.log("Working")
        }
    }
});