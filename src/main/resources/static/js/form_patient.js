document.addEventListener('DOMContentLoaded', function () {
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