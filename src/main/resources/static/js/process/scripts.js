function configConExams(id){

    //Essa configuração realiza o consulta sempre assim que o resultado finalizar
    $('#select-exams').select2({

        ajax: {
            url: 'http://localhost:8080/exam/get/'+id,
            dataType: 'json',
            data: function (params) {
                var query = {
                    search: params.term,
                    page: params.page || 1
                }
                return query;

            },
            processResults: function (data, params) {
                params.page = params.page || 1;

                //Verifica se é a última página para adicionar o botão de adicionar um novo cliente
                // if((params.page * 5) >= data.total){
                //     data.data.push(getBtnAdicionarModal('btn-md-add-pessoa'))
                // }
                return {
                    results: data.data,
                    pagination: {
                        more: (params.page * 5) < data.total
                    }
                };
            },
            cache: true
        },
        placeholder: "Informe o exame ...",
        escapeMarkup: function (markup) {
            return markup;
        }, // let our custom formatter work
        minimumInputLength: 0,
        templateResult: formatRepo,
        templateSelection: formatRepoSelection,
        language: 'pt-BR'


    });


}

function formatRepo (repo) {
    if (repo.loading) {
        return repo.text;
    }

    var markup = "";

    if(repo.id == 0 ){
        markup = repo.body;
    }else{
        markup = "<div class='select2-result-repository clearfix'>" +
            //            "<div class='select2-result-repository__avatar'><img src='" + repo.owner.avatar_url + "' /></div>" +
            "<div class='select2-result-repository__meta'>" +
            "<div class='select2-result-repository__title'>" + repo.nome + "</div>";

        if (repo.description) {
            markup += "<div class='select2-result-repository__description'>" + repo.description + "</div>";
        }

        markup += "<div class='select2-result-repository__statistics'>" +
            "<div class='select2-result-repository__forks'><i class='fa fa-map-marker'></i> " + repo.endereco + "</div>" +
            "<div class='select2-result-repository__stargazers'><i class='fa fa-phone'></i> " + repo.telefone + "</div>" +
            "</div>" +
            "</div></div>";
    }



    return markup;
}

function formatRepoSelection (repo) {
    return repo.nome || repo.text;
}