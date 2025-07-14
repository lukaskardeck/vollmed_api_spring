package med.voll.api.domain.consulta.validacao;

import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.exception.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorHorarioComAntecedencia implements ValidadorAgendamentoConsulta{

    @Override
    public void validar(DadosAgendamentoConsulta dados) {
        var dataConsulta = dados.data();
        var dataAgora = LocalDateTime.now();
        var diferencaEmMinutos = Duration.between(dataAgora, dataConsulta).toMinutes();

        if (diferencaEmMinutos < 30) {
            throw new ValidacaoException("Consulta deve ser agendada com antecedência mínima de 30 minutos!");
        }
    }
}
