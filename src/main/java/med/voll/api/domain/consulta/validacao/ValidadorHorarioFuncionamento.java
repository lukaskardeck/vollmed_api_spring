package med.voll.api.domain.consulta.validacao;

import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.exception.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidadorHorarioFuncionamento implements ValidadorAgendamentoConsulta{

    @Override
    public void validar(DadosAgendamentoConsulta dados) {
        var dataConsulta = dados.data();

        // Verificar se a consulta está marcada para um domingo
        var consultaNoDomingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);

        // Verifica se a consulta está marcada para iniciar antes das 07:00h
        var consultaAntesDaAbertura = dataConsulta.getHour() < 7;

        // Verifica se a consulta está marcada para iniciar após às 18:00h
        var consultaAposAbertura = dataConsulta.getHour() > 18;

        if (consultaNoDomingo || consultaAntesDaAbertura || consultaAposAbertura) {
            throw new ValidacaoException("Consulta fora do período de funcionamento da clínica!");
        }

    }
}
