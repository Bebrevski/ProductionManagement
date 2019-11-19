"use strict";

class Stock {
    constructor() {
        this.id = 0;
        this.uuid = '';
        this.stockTypeId = 0;
        this.lastModified = '';
        this.materials = [];
        this.production = {};
        this.stockType = {};
        this.inCreateMode = false;
        this.inPreviewMode = false;
    }
}