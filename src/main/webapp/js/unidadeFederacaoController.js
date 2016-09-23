$('#unidadeFederativaForm').submit(function (event) {

    var id = $("#txtId").val();
    if (id == "") {
        id = null;
    }

    var unidadeFederacao = {
        id: id,
        sigla: $("#txtSigla").val(),
        descricao: $("#txtDescricao").val(),
        codigoIBGE: $("#txtCodigoIBGE").val(),
        pais: {
            id: $("#comboPais").val()
        }
    };

    var data = {
        json: JSON.stringify(unidadeFederacao)
    };

    $.ajax({
        method: 'POST',
        url: '/sias/cadastrosBasicos/unidadeFederacao/save',
        data: data,
        success: function (data) {
            if (data != null) {
                var success = data.success;
                if (success) {
                    var voltarListagem = function () {
                        document.location.assign('/sias/cadastrosBasicos/unidadeFederacao');
                    };
                    Msg.notify(data.msg, 'success', 2000, null, voltarListagem);
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

