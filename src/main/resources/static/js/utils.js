deletaInputHiddenCheckbox();
ativaBtnDelete();
ativaDatePicker();
ativaSelect();
// ativaModal();
ativaTooltip();

/**
 * Está função foi necessária pois o framework Materialize utiliza um padrão de checkbox e o Thymeleaf quebra o
 * padrão utilizado no materialize. O Thymeleaf, cria inputs com tipo hidden junto com os checkbox,
 * o que é removido com essa função
 */
function deletaInputHiddenCheckbox(){
    var inputs = document.getElementsByTagName('input');

    for(var i = inputs.length-1; i >= 0; i--){
        if(inputs[i].type === "hidden" && inputs[i].value === "on"){
            inputs[i].remove();
        }
    }
}

/**
 * Função para ativar o modal do Materialize
 */
function ativaModal(){
    document.addEventListener('DOMContentLoaded', function () {
        var elems = document.querySelectorAll('.modal');
        var instances = M.Modal.init(elems, options);
    });
}

/**
 * Função para ativar o select do materialize
 */
function ativaSelect(){
    document.addEventListener('DOMContentLoaded', function () {
        var selectElements = document.querySelectorAll('select');
        M.FormSelect.init(selectElements, {});
    });
}

/**
 * função para ativar o DatePicker do materialize
 */
function ativaDatePicker(){
    document.addEventListener('DOMContentLoaded', function () {
        var datePickerElements = document.querySelectorAll('.datepicker');
        var datePickerOptions = {
            container: 'body',
            showDaysInNextAndPreviousMonths: true,
            format: "dd/mm/yyyy",
            i18n: {
                today: 'Hoje',
                clear: 'Limpar',
                done: 'Ok',
                nextMonth: 'Próximo mês',
                previousMonth: 'Mês anterior',
                weekdaysAbbrev: ['D', 'S', 'T', 'Q', 'Q', 'S', 'S'],
                weekdaysShort: ['Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'Sáb'],
                weekdays: ['Domingo', 'Segunda-Feira', 'Terça-Feira', 'Quarta-Feira', 'Quinta-Feira', 'Sexta-Feira', 'Sábado'],
                monthsShort: ['Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun', 'Jul', 'Ago', 'Set', 'Out', 'Nov', 'Dez'],
                months: ['Janeiro', 'Fevereiro', 'Março', 'Abril', 'Maio', 'Junho', 'Julho', 'Agosto', 'Setembro', 'Outubro', 'Novembro', 'Dezembro']
            }
        };
        M.Datepicker.init(datePickerElements, datePickerOptions);
    });
}

/**
 * Função para ativar uma confirmação antes de excluir nos botões que possuem a classe delete
 */
function ativaBtnDelete(){
    var btn_delete = document.getElementsByClassName('delete');
    for(var i = 0; i < btn_delete.length; i++){
        btn_delete[i].addEventListener('click', function (ev) {
            if(confirm('Deletar esse registro?')){
                window.location.href = ev.srcElement.parentElement.dataset['url'];
            }
        });
    }
}

function ativaTooltip(){
    document.addEventListener('DOMContentLoaded', function() {
        var elems = document.querySelectorAll('.tooltipped');
        var instances = M.Tooltip.init(elems, {position: 'top'});
    });
}


$.validator.setDefaults({
    errorClass: 'invalid',
    validClass: "valid",
    errorPlacement: function(error, element) {
        $(element)
            .closest("form")
            .find("label[for='" + element.attr("id") + "']")
            .parent()
            .find('span')
            .remove();
        $(element)
            .closest("form")
            .find("label[for='" + element.attr("id") + "']")
            .after('<span class="helper-text" data-error="'+ error.text() +'" ></span>')
    },
    submitHandler: function(form) {
    }
});