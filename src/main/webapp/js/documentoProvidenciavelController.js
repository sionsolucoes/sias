$('#documentoProvidenciavelForm').submit(function (event) {

    var id = $("#txtId").val();
    if (id == "") {
        id = null;
    }

    var documentoProvidenciavel = {
        id: id,
        descricao: $("#txtDescricao").val(),
        codigo: $("#txtCodigo").val()
    };

    var data = {
        json: JSON.stringify(documentoProvidenciavel)
    };
    
    Msg.bar('Salvando. Por favor, aguarde...', null);

    $.ajax({
        method: 'POST',
        url: '/cadastrosBasicos/documentoProvidenciavel/save',
        data: data,
        success: function (data) {
            if (data != null) {
                var success = data.success;
                if (success) {
                    var voltarListagem = function () {
                        document.location.assign('/cadastrosBasicos/documentoProvidenciavel');
                    };
                    Msg.notify(data.msg, 'success', 1000, null, voltarListagem);
                } else {
                    Msg.notify(data.msg, 'warning');
                }
            }
        },
        failure: function (data) {
            Msg.notify('Erro inesperado. Contate o adminstrador do Sistema', 'warning');
        }
    });

    return false;
});
















