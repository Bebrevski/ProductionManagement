"use strict"

function handleFileUpload(error, file, component) {
    if (error === null) {

        var newFile = new AttachedDocument();
        newFile.GUID = file.serverId;
        newFile.FileSize = file.fileSize;
        newFile.isNew = true;

        // add guid to the Receip/Expeditet Protocol Attachments list so it can be later traced
        component.Protocol.AttachedDocumentList.push(newFile);

        component.submitBtnDisabled = false;
        return file;
    }
    else {
        iziToast.error({
            title: 'Грешка!'
        });
        console.error(error);
    }
};

function handleDownloadFile(file) {
    /// If the clicked file doesm't have a serverId, don't try to download it.
    /// It occurs when it's a error file (did't pass validation) or it's still uploading
    if (file.serverId === null) return;

    promptActionConfirmation("Сваляне на файла?", () => {
        ///compone link and download file
        const link = document.createElement('a')
        link.href = "/api/AttachedDocument/DownloadFile?attachedDocumentGuid=" + file.serverId;
        link.target = "_blank";
        link.click();
    });
}

async function handleRemoveFile(file, component) {
    /// If the clicked file doesm't have a serverId, don't try to download it.
    /// It occurs when it's a error file (did't pass validation) or it's still uploading
    if (file.serverId === null) return false;

    var result = await new Promise(resolve => {
        promptActionConfirmation("Премахване на файла?", () => {
            makeServerCall('post', '/api/AttachedDocument/RemoveFile?attachedDocumentGuid=' + file.serverId, null, (ResultData) => {
                const index = component.Protocol.ReceivedWasteList.findIndex(x => x.GUID === file.serverId);
                component.Protocol.AttachedDocumentList.splice(index, 1);
                resolve(true);
            });
        })
    });

    return result;
}