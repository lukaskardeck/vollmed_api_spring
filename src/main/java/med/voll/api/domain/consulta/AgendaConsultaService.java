package med.voll.api.domain.consulta;

import med.voll.api.domain.consulta.validacao.ValidadorAgendamentoConsulta;
import med.voll.api.domain.exception.ValidacaoException;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private List<ValidadorAgendamentoConsulta> validadores;

    public DadosDetalhamentoConsulta agendar(DadosAgendamentoConsulta dados) {

        // Cliente informou o Id do médico, mas o médico não existe no banco de dados
        if (dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())) {
            throw new ValidacaoException("Não foi encontrado no sistema um médico com o ID informado!");
        }

        // Cliente informou o Id do paciente, mas não existe no banco de dados
        if (!pacienteRepository.existsById(dados.idPaciente())) {
            throw new ValidacaoException("Não foi encontrado no sistema um paciente com o ID informado!");
        }

        // Realiza a validação dos dados, conforme as regras de negócio
        validadores.forEach(v -> v.validar(dados));

        var paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        var medico = escolherMedico(dados);

        if (medico == null) {
            throw new ValidacaoException("Não existe médico com a especialidade informada disponível na data e hora declarada!");
        }

        // Gera uma consulta
        var consulta = new Consulta(null, medico, paciente, dados.data());

        // Salva a consulta no banco
        consultaRepository.save(consulta);

        return new DadosDetalhamentoConsulta(consulta);
    }

    private Medico escolherMedico(DadosAgendamentoConsulta dados) {
        if (dados.idMedico() != null) {
            return medicoRepository.getReferenceById(dados.idMedico());
        }

        if (dados.especialidade() == null) {
            throw new ValidacaoException("Se o médico não é informado, se torna obrigatório informar uma especialidade!");
        }

        return medicoRepository.buscarMedicoAleatorioDeEspecialidadeLivreNaData(dados.especialidade(), dados.data());
    }

}
