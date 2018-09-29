document.getElementById('name').focus();
var form = $('form');
var dias_atendimento = $('#dias_atendimento');
var erro_atendimento = $('.erro_atendimento');
var formvalidate = form.validate({
    rules:{
        name:{
            required: true
        },
        crm:{
            required: true,
            number: true
        },
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
        form.submit();
    }
}