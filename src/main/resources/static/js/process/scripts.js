var $MEDICAL_PROCEDURE      = $('#medicalProcedure');
var $DOCTOR                 = $('#doctor');
var $PATIENT                = $('#patient');
var $ID                     = $('#id');
var $EXAM                   = $('#exam');
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
    getForm();
});

function getForm() {
    processo.id                 = parseFloat($ID.val());
    processo.doctor             = isNaN(parseFloat($DOCTOR.val()))              ? 0 : parseFloat($DOCTOR.val());
    processo.patient            = isNaN(parseFloat($PATIENT.val()))             ? 0 : parseFloat($PATIENT.val());
    processo.medicalProcedure   = isNaN(parseFloat($MEDICAL_PROCEDURE.val()))   ? 0 : parseFloat($MEDICAL_PROCEDURE.val());
    processo.procesexams        = getTable();
}

function changeApproved(elemento){
    var index = parseFloat(elemento.parentElement.parentElement.parentElement.parentElement.dataset.index);
    index = getProcessExam(index);
    processo.procesexams[index].approved = elemento.checked;
}

function getProcessExam(index){
    for(var i = 0; i < processo.procesexams.length; i++){
        if(processo.procesexams[i].index === index){
            return i;
        }
    }
}


function getTable(){
    var exames          = [];
    var $TR_EXAMES      = $('#table_exames tbody tr');
    var qtdexames       = $TR_EXAMES.length;
    var process_id      = parseFloat($ID.val());
    for(var i = 0; i < qtdexames; i++){
        var id          = parseFloat($TR_EXAMES[i].id);
        var exam_id     = parseFloat($TR_EXAMES[i].querySelector('td span').textContent);
        var approved    = $TR_EXAMES[i].querySelector('input').checked;
        var index       = parseFloat($TR_EXAMES[i].dataset.index);
        exames          .push({index: index, id: id ,exams: exam_id, approved: approved, excluido: false, process: process_id});
    }
    return exames;
}

function changeDoctor(){
    processo.doctor = parseFloat($DOCTOR.val());
}

function changeExam(idExam){
    var index;
    if(processo.procesexams.length > 0){
        index = (processo.procesexams[processo.procesexams.length-1].index + 1);
    }else{
        index = 1;
    }
    var request = $.ajax({
        url: URL_BASE+"/exam/find/"+idExam,
        method: 'get',
        statusCode:{
            200: function (data) {
                if(data !== null){
                    processo.procesexams.push({
                        index: index,
                        exams: data.id,
                        approved: false,
                        excluido: false
                    });
                    incluirLinhaTabelaExames(data, index);
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

$EXAM.on('change', function () {
    var idExam = $(this).val();
    changeExam(idExam);
});

$BTN_SALVAR.on('click', function () {
    clickBtnSalvar();
});

$HTML.on('click', '.remover_exam', function () {
    clickRemoverExam(this);
});

function preencherTabelaExames(exames) {
    var index = processo.procesexams.length;
    for(var i =0; i<exames.length; i++){
        index += 1;
        processo.procesexams.push({
            index: index,
            exams: exames[i].id,
            approved: false,
            excluido: false
        });
        incluirLinhaTabelaExames(exames[i], index);
    }
}

function clickRemoverExam(elemento) {
    var $ELEMENTO = $(elemento);
    var index = $ELEMENTO.attr('data-index');
    processo.procesexams[index-1].excluido = true;
    $ELEMENTO.parent().parent().remove();
}

function incluirLinhaTabelaExames(exame, index){
    var newRow = $("<tr data-index='"+index+"' id='tr-exame-"+index+"' >");
    var cols = "";
    cols += '<td class="codigo_exam">'+exame.id+'</td>';
    cols += '<td class="nome_exam">'+exame.name+'</td>';
    cols += '<td class="tempo_jejum">'+exame.jejumTime+'</td>';
    cols += '<td class="aprovado">'+'<p><label><input type="checkbox" class="filled-in"><span></span></label></p>'+'</td>';
    cols += '<td class="acoes"><a class="waves-effect waves-light btn remover_exam" data-index="'+index+'" data-id="'+exame.id+'">Remover</a></td>';

    newRow.append(cols);
    $TABLE_EXAMES.append(newRow);
}

function clickBtnSalvar(){
    removerExamesExcluidos();
    if(processo.id === 0 ){
        processo.id.delete();
    }
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

function removerExamesExcluidos() {
    var processlength = processo.procesexams.length-1;
    for(var i = processlength; i >= 0; i--){
        if(processo.procesexams[i].excluido === true){
            processo.procesexams.splice(i, 1);
        }
    }
}