var filtro_name     = $('#filtro_name');
var filtro_crm      = $('#filtro_crm');


$(document).ready(function () {
    filtro_name.focus();
});

function limparcampos(){
    filtro_name.value = null;
    filtro_crm.value = null;

}