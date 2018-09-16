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
    }
    M.Datepicker.init(datePickerElements, datePickerOptions);

    var selectElements = document.querySelectorAll('select');
    M.FormSelect.init(selectElements, {});
    /*<![CDATA[*/
    var cityOptions = /*[[${cityOptions}]]*/ [];
    var ufOptions = /*[[${ufOptions}]]*/ [];
    /*]]>*/
    var cityAutocomplete = document.querySelector("#city");
    M.Autocomplete.init(cityAutocomplete, {
        data: cityOptions
    });
    var ufAutocomplete = document.querySelector("#uf");
    M.Autocomplete.init(ufAutocomplete, {
        data: ufOptions
    });

});