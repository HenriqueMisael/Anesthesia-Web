var $NOME = $('name');
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
if($NOME.length > 0){
    $NOME.focus();
}

var form = $('#form_doctor');
var erro_atendimento = $('.erro_atendimento');
var formvalidate = form.validate({
    rules:{
        name:{
            required: true
        },
        crm:{
            required: true,
            number: true
        }
    },
    messages:{
        name: "Por favor, preencha um nome",
        crm:{
            required: "Por favor, preencha o crm",
            number: "Preencha apenas números"
        }
    }
});

function verificaDiasAtendimento(){
    if(segundaManha.prop('checked') || segundaTarde.prop('checked') || tercaManha.prop('checked')  || tercaTarde.prop('checked')  ||
        quartaManha.prop('checked')  || quartaTarde.prop('checked')  || quintaManha.prop('checked') || quintaTarde.prop('checked') ||
        sextaManha.prop('checked')   || sextaTarde.prop('checked')   || sabadoManha.prop('checked') || sabadoTarde.prop('checked') ||
        domingoManha.prop('checked') || domingoTarde.prop('checked')){
        erro_atendimento.attr('hidden', true);
        return true;
    }
    erro_atendimento.attr('hidden', false);
    return false;
}

function validaform(){
    if(formvalidate.form() && verificaDiasAtendimento()){
        form[0].submit();
    }
}