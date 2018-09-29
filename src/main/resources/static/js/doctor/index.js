var filtro_name     = $('#filtro_name');
var filtro_crm      = $('#filtro_crm');
var segundaManha    = $('#segundaManha1');
var segundaTarde    = $('#segundaTarde1');
var tercaManha      = $('#tercaManha1');
var tercaTarde      = $('#tercaTarde1');
var quartaManha     = $('#quartaManha1');
var quartaTarde     = $('#quartaTarde1');
var quintaManha     = $('#quintaManha1');
var quintaTarde     = $('#quintaTarde1');
var sextaManha      = $('#sextaManha1');
var sextaTarde      = $('#sextaTarde1');
var sabadoManha     = $('#sabadoManha1');
var sabadoTarde     = $('#sabadoTarde1');
var domingoManha    = $('#domingoManha1');
var domingoTarde    = $('#domingoTarde1');


$(document).ready(function () {
    filtro_name.focus();
});

function limparcampos(){
    filtro_name.value = null;
    filtro_crm.value = null;

}