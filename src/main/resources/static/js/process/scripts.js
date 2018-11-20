var $MEDICAL_PROCEDURE      = $('#medicalProcedure');
var $DOCTOR                 = $('#doctor');
var $PATIENT                = $('#patient');
var $ID                     = $('#id');
var $TABLE_EXAMES           = $('#table_exames');
var $BTN_SALVAR             = $('#btn_salvar');
var $HTML                   = $('html');
var processo = {
    id: 0,
    active: true,
    medicalProcedure: {},
    doctor: {},
    patient: {},
    procesexams: []
};
$(document).ready(function () {
    if( window.location.href.split('/')[3]+'/'+window.location.href.split('/')[4] === 'process/new'){
        getForm();
    }
});

function getForm() {
    processo.id                 = parseFloat($ID.val());
    processo.doctor             = parseFloat($DOCTOR.val());
    processo.patient            = parseFloat($PATIENT.val());
    processo.medicalProcedure   = parseFloat($MEDICAL_PROCEDURE.val());
    processo.procesexams        = getTable();
}

function changeApproved(elemento){
    var exam_id = parseFloat(elemento.parentElement.parentElement.parentElement.parentElement.id);
    var index = getProcessExam(exam_id);
    processo.procesexams[index].approved = elemento.checked;
}

function getProcessExam(id){
    for(var i = 0; i < processo.procesexams.length; i++){
        if(processo.procesexams[i].exams === id){
            return i;
        }
    }
}


function getTable(){
    var exames          = [];
    var $TR_EXAMES      = $('tbody tr');
    var qtdexames       = $TR_EXAMES.length;
    for(var i = 0; i < qtdexames; i++){
        var id          = parseFloat($TR_EXAMES[i].querySelector('td span').textContent);
        var approved    = $TR_EXAMES[i].querySelector('input').checked;
        exames          .push({exams: id, approved: approved});
    }
    return exames;
}

function changeDoctor(){
    processo.doctor = parseFloat($DOCTOR.val());
}

function changePatient(){
    processo.patient = parseFloat($PATIENT.val());
}

function changeMedicalProcedure(idMedicalprocedure){
    processo.medicalProcedure = parseFloat($MEDICAL_PROCEDURE.val());
    var request = $.ajax({
        url: URL_BASE+"/medicalProcedure/find/"+idMedicalprocedure,
        method: 'get',
        statusCode:{
            200: function (data) {
                if(data !== null){
                    preencherTabelaExames(data.exams);
                }
            },
            500: function (data_error) {
                console.log(data_error)
            }
        }
    });
    request.always(function () {
    });
}

$HTML.on('change', '.approved_change', function () {
    changeApproved(this);
});

$MEDICAL_PROCEDURE.on('change', function () {
    var idMedicalprocedure = $(this).val();
    changeMedicalProcedure(idMedicalprocedure);
});

$DOCTOR.on('change', function () {
    changeDoctor();
});

$PATIENT.on('change', function () {
    changePatient();
});

$BTN_SALVAR.on('click', function () {
    clickBtnSalvar();
});

function preencherTabelaExames(exames) {
    for(var i =0; i<exames.length; i++){
        processo.procesexams.push({exams: exames[i].id,
            approved: false});
        incluirLinhaTabelaExames(exames[i]);
    }
}

function incluirLinhaTabelaExames(exame){
    var newRow = $("<tr id='tr-exame-"+exame.id+"' >");
    var cols = "";
    cols += '<td class="codigo_exam">'+exame.id+'</td>';
    cols += '<td class="nome_exam">'+exame.name+'</td>';
    cols += '<td class="tempo_jejum">'+exame.jejumTime+'</td>';
    cols += '<td class="aprovado">'+'<p><label><input type="checkbox" class="filled-in"><span></span></label></p>'+'</td>';

    newRow.append(cols);
    $TABLE_EXAMES.append(newRow);
}

function clickBtnSalvar(){
    var request = $.ajax({
        url: URL_BASE+"/process",
        method: 'POST',
        data: JSON.stringify(processo),
        dataType: 'json',
        contentType: "application/json",
        processData: true,
        statusCode:{
            200: function (data) {
                if(data.id > 0){
                    window.location.href = URL_BASE+"/process";
                }else{
                    alert('Erro ao salvar processo, preencha o dados novamente.');
                }
            },
            500: function (data_error) {
                console.log(data_error)
            }
        }
    });
    request.always(function () {
    });
}

function montarForm(){
    var form = new FormData();
    form.append('medicalProcedure', processo.medicalProcedure);
    form.append('doctor', processo.doctor);
    form.append('patient', processo.patient);
    for(var i = 0; i < processo.procesexams.length; i++){
        form.append('procesexams['+i+'].exams', processo.procesexams[i].exams);
        form.append('procesexams['+i+'].approved', processo.procesexams[i].approved);
    }
    return form;
}