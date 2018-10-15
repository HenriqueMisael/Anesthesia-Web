var filtro_name     = $('#filtro_name');
var for_filtro_name = $('[for=filtro_name]');
var filtro_crm      = $('#filtro_crm');
var for_filtro_crm  = $('[for=filtro_crm]');


$(document).ready(function () {
    filtro_name.focus();
});

function limparcampos(){
    filtro_name.val('');
    for_filtro_name.removeClass('active');
    filtro_crm.val('').removeClass('active');
    for_filtro_crm.removeClass('active');
    $('#filtro_ativo').parent().find('input')[0].value = "Sim";
}