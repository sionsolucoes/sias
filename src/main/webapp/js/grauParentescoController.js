$('#grauParentescoForm').submit(function (event) {

    var id = $("#txtId").val();
    if (id == "") {
        id = null;
    }

    var grauParentesco = {
        id: id,
        codigo: $("#txtCodigo").val(),
        descricao: $("#txtDescricao").val()
    };

    var data = {
        json: JSON.stringify(grauParentesco)
    };

    Msg.bar('Salvando. Por favor, aguarde...', null);

    $.ajax({
        method: 'POST',
        url: '/cadastrosBasicos/grauParentesco/save',
        data: data,
        success: function (data) {
            if (data != null) {
                var success = data.success;
                if (success) {
                    var voltarListagem = function () {
                        document.location.assign('/cadastrosBasicos/grauParentesco');
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













