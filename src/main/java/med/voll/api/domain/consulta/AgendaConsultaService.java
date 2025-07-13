package med.voll.api.domain.consulta;

import med.voll.api.domain.exception.ValidacaoException;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendaConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    public void agendar(DadosAgendamentoConsulta dados) {

        // Cliente informou o Id do médico, mas o médico não existe no banco de dados
        if (dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())) {
            throw new ValidacaoException("Não foi encontrado no sistema um médico com o ID informado!");
        }

        var medico = escolherMedico(dados);

        // Tenta buscar o paciente no banco, se não existir, retorna uma exception
        var paciente = pacienteRepository.findById(dados.idPaciente())
                .orElseThrow(() -> new ValidacaoException("Não foi encontrado no sistema um paciente com o ID informado!"));

        // Gera uma consulta
        var consulta = new Consulta(null, medico, paciente, dados.data());

        // Salva a consulta no banco
        consultaRepository.save(consulta);
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
