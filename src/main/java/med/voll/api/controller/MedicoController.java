package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("medico")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    /*
    * Cadastra um novo médico
    */
    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dadosCadastroMedico) {
        medicoRepository.save(new Medico(dadosCadastroMedico));
    }


    /*
     * Lista todos os médicos ativos no sistema
     */
    @GetMapping
    public ResponseEntity<Page<DadosListagemMedico>> listar(@PageableDefault(sort = {"nome"}) Pageable paginacao) {
        var pageMedicos = medicoRepository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);

        return ResponseEntity.ok(pageMedicos);
    }


    /*
     * Atualiza informações específicas do médico (consultar essas informações no DTO DadosAtualizacaoMedico)
     */
    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody DadosAtualizacaoMedico dados) {
        var medico = medicoRepository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }


    /*
     * Faz a exclusão lógica de um médico (seu campo "ativo" se torna 'false')
     */
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletar(@PathVariable Long id) {
        var medico = medicoRepository.getReferenceById(id);
        medico.deletar();

        return ResponseEntity.noContent().build();
    }
}
