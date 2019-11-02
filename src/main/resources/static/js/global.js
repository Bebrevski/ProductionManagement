/// enums and consts
var ResultType = {
    Success: 1,
    Error: 0,
    Exception: -1
};

var UserAction = {
    CreateReceiptProtocol: 1,
    PreviewReceiptProtocol: 2,
    EditReceiptProtocol: 3,
    DeleteReceiptProtocol: 4,
    CreateExpediteProtocol: 5,
    PreviewExpediteProtocol: 6,
    EditExpediteProtocol: 7,
    DeleteExpediteProtocol: 8,
    InventoryPreview: 9,
    ReportBookPreview: 10,
    AttachedDocumentsPreview: 11,
    ReportPreview: 12,
    AdminNumenclatures: 13,
    AdminCollectionCenters: 14,
    AdminMobileCenters: 15,
    AdminUsers: 16,
    AdminRoleActions: 17,
    CreateInspection: 18,
    PreviewInspection: 19,
    EditInspection: 20,
    DeleteInspection: 21,
};

///Get new GUID from server
function getNewGuid() {
    return axios.post('/Base/NewGuid')
        .catch(function (error) {
            console.error("Error while getting new GUID", error);
        });
}

/// loading animation
function loadingShow() {
    $('body').loadingModal({
        animation: 'fadingCircle'
    });
}

function loadingHide() {
    $('body').loadingModal('destroy');
}

///constrants for promptActionConfirmation
const questionToBeDeleted = 'Изтриване на запис?';
const questionToActivate = 'Желаете ли да активирате задачата?';
const questionToDeactivate = 'Маркирай като завършена';

const EmptyGuid = "00000000-0000-0000-0000-000000000000";

function getErrorMessages(messagesJSON) {
    try {
        return JSON.parse(messagesJSON).join("<br/>");
    }
    catch (error) {
        return "";
    }

}

///Prompt the user for confirmation. If YES, execute the desired function
function promptActionConfirmation(question, executeFunc) {
    iziToast.question({
        timeout: 20000,
        close: false,
        overlay: true,
        displayMode: 'once',
        closeOnEscape: true,
        transitionIn: 'flipInX',
        transitionOut: 'flipOutX',
        id: 'question',
        zindex: 1000,
        title: question,
        position: 'center',
        buttons: [
            ['<button><b>Да</b></button>', function (instance, toast) {
                /// execute the desired function
                executeFunc();

                instance.hide({ transitionOut: 'flipOutX' }, toast, 'button');
            }, true],
            ['<button>Не</button>', function (instance, toast) {
                /// If No is clicked
                instance.hide({ transitionOut: 'flipOutX' }, toast, 'button');
                return false;
            }]
        ]
    });
}

///Make requests to server, expecting OperationalResult<T> response. 
///If response is success it executes the onSuccessCode using the ResultData as parameter
function makeServerCall(verb, url, payload, onSuccessCode) {
    loadingShow();

    axios({
        headers: {
            'X-Requested-With': 'XMLHttpRequest' // mark as an ajax request. Works in cooperation with AjaxAuthorize filter attribute
        },
        method: verb,
        url: url,
        data: payload
    })
        .then(function (response) {
            let result = response.data;

            if (result.Type === ResultType.Success) {

                /// execute the desired code
                onSuccessCode(result.ResultData);

                if (result.Message !== null) {
                    iziToast.success({
                        message: result.Message
                    });
                }
            }
            else {
                iziToast.warning({
                    layout: 2,
                    title: result.Message,
                    message: result.AdditionalMessages !== null ? result.AdditionalMessages.join("<br/>") : ""
                });
            }
        })
        .catch(function (error) {
            if (error.response.status === 403) {
                //If the response is 'not authorized' => display the content. It's a meaningfull message
                iziToast.error({
                    layout: 2,
                    title: 'Грешка!',
                    message: error.response.data
                });
            }
            else {
                iziToast.error({
                    title: 'Грешка!'
                });
            }
            console.error(error);
        })
        .finally(() => {
            loadingHide();
        });
}

/// MEthod for hard copying objects
function copyObject(src) {
    var clone1 = JSON.parse(JSON.stringify(src));
    //var clone2 = jQuery.extend(true, {}, src); //Different way of cloning

    return clone1;
}

/// init izi Modal
$("#modal").iziModal();

/// loading animation for table
function loadingTableShow() {
    $('#result-datatable').loadingModal({
        animation: 'fadingCircle',
        backgroundColor: 'SteelBlue'
    });
}

function loadingTableHide() {
    $('#result-datatable').loadingModal('destroy');
}

// used to convert all displayed date and time in requested format
function dateAndTimeFormated(date) {
    let convertedDate = moment(date).format('DD.MM.YYYY HH:mm');
    if (convertedDate == "Invalid date") {
        return date;
    }
    return convertedDate;
}

// used to convert all displayed dates in requested format
function dateFormated(date) {
    var convertedDate = moment(date).format('DD.MM.YYYY');
    if (convertedDate == "Invalid date") {
        return date;
    }
    return convertedDate;
}

// used to convert all displayed dates in format moment(date).calendar() -> Днес в 12:13
function dateFormater(date) {
    let convertedDate = moment(date).calendar(null, {
        sameDay: '[Днес ]' + 'HH:mm',
        lastDay: '[Вчера ]' + 'HH:mm',
        lastWeek: 'DD/MM/YYYY HH:mm',
        sameElse: 'DD/MM/YYYY HH:mm'
    });
    if (convertedDate == "Invalid date") {
        return date;
    }
    return convertedDate
}

function loadAllowedActions(vue) {
    makeServerCall("GET", "/api/UserManagement/GetUserAllowedActions", null, (ResultData) => {
        vue.AllowedActions = ResultData;
    });
}