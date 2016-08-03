$('#abaSaude').ready(function (event) {

    var txtDeficiencia = $('#txtDeficiencia');
    var deficienciaId = txtDeficiencia.val();
    var comboDeficiencia = $('#comboDeficiencia');

    $.ajax({
        method: 'POST',
        url: '/sias/controleFamiliar/deficiencia/readAll',
        success: function (data) {
            if (data !== null) {
                for (var i = 0; i < data.length; i++) {
                    var deficiencia = data[i];
                    var option = document.createElement("option");
                    option.selected = deficienciaId === deficiencia.id;
                    option.value = deficiencia.id;
                    option.text = deficiencia.codigo + ' - ' + deficiencia.descricao;
                    comboDeficiencia.append(option);
                }
            }
        }
    });
});

$('#checkNecessitaCuidadosConstantes').change(function () {

    var checkNecessitaCuidadosConstantes = $('#checkNecessitaCuidadosConstantes');
    var txtDescricaoNecessidadeCuidadosConstantes = $('#txtDescricaoNecessidadeCuidadosConstantes');

    if (checkNecessitaCuidadosConstantes.prop('checked')) {
        txtDescricaoNecessidadeCuidadosConstantes.prop('disabled', false);
    } else {
        txtDescricaoNecessidadeCuidadosConstantes.val('');
        txtDescricaoNecessidadeCuidadosConstantes.prop('disabled', true);
    }

});

$('#checkGestante').change(function () {

    var checkGestante = $('#checkGestante');
    var txtMesesGestacao = $('#txtMesesGestacao');
    var checkIniciouPreNatal = $('#checkIniciouPreNatal');

    if (checkGestante.prop('checked')) {
        txtMesesGestacao.prop('disabled', false);
        checkIniciouPreNatal.prop('disabled', false);
    } else {
        txtMesesGestacao.prop('disabled', true);
        checkIniciouPreNatal.prop('disabled', true);
    }

});