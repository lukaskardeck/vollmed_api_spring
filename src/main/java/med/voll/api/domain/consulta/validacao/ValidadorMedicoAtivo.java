package med.voll.api.domain.consulta.validacao;

import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.exception.ValidacaoException;
import med.voll.api.domain.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoAtivo implements ValidadorAgendamentoConsulta {

        @Autowired
        private MedicoRepository medicoRepository;

        @Override
        public void validar(DadosAgendamentoConsulta dados) {
            // escolha do médico opcional
            if (dados.idMedico() == null) return;

            var medicoAtivo = medicoRepository.findAtivoById(dados.idMedico());
            if (!medicoAtivo) {
                throw new ValidacaoException("Consulta não pode ser agendada com um médico inativo!");
            }
        }
}
