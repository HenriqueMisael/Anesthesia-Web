var $MEDICAL_PROCEDURE      = $('#medicalProcedure');
var $DOCTOR                 = $('#doctor');
var $PATIENT                = $('#patient');
var $TABLE_EXAMES           = $('#table_exames');
var $BTN_SALVAR             = $('#btn_salvar');
var processo = {
    medicalProcedure: {},
    doctor: {},
    patient: {},
    procesexams: []
};

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
        data: processo,
        dataType: 'json',
        contentType: "application/json",
        processData: true,
        statusCode:{
            200: function (data) {
                console.log(data);
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