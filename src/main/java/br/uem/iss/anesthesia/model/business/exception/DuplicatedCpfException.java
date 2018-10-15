package br.uem.iss.anesthesia.model.business.exception;

import br.uem.iss.anesthesia.model.entity.PatientModel;

public class DuplicatedCpfException extends BusinessRuleException {

    public DuplicatedCpfException(PatientModel patientModel) {
        super("O paciente " + patientModel + " já está cadastrado com o cpf " + patientModel.getCpf());
    }
}
