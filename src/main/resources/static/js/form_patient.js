document.addEventListener('DOMContentLoaded', function () {
    /*<![CDATA[*/
    var cityOptions = /*[[${cityOptions}]]*/ [];
    var ufOptions = /*[[${ufOptions}]]*/ [];
    /*]]>*/
    var cityAutocomplete = document.querySelector("#city");
    M.Autocomplete.init(cityAutocomplete, {
        data: {
            'Maringa': null
        }
    });
    var ufAutocomplete = document.querySelector("#uf");
    M.Autocomplete.init(ufAutocomplete, {
        data: ufOptions
    });

});