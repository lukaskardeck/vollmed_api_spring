package med.voll.api.domain.consulta.validacao;

import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoComOutraConsultaNaMesmaDataHora implements ValidadorAgendamentoConsulta {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Override
    public void validar(DadosAgendamentoConsulta dados) {
        var medicoComOutraConsultaNaMesmaDataHora = consultaRepository.existsByMedicoIdAndData(dados.idMedico(), dados.data());

        if (medicoComOutraConsultaNaMesmaDataHora) {
            throw new ValidacaoException("Médico já possui outra consulta agendada nesse mesmo horário");
        }

    }
}
