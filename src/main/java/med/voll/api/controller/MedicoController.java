package med.voll.api.controller;

import lombok.AllArgsConstructor;
import med.voll.api.assembler.MedicoAssembler;
import med.voll.api.dto.AtualizacaoMedicoDTO;
import med.voll.api.dto.MedicoDTO;
import med.voll.api.dto.MedicoResumoDTO;
import med.voll.api.medico.DadosDetalhamentoMedico;
import med.voll.api.medico.Medico;
import med.voll.api.medico.MedicoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/medicos")
@AllArgsConstructor
public class MedicoController {

    private MedicoRepository repository;

    private MedicoAssembler medicoAssembler;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid MedicoDTO medicoDTO, UriComponentsBuilder uriBuilder) {
        Medico medico = medicoAssembler.toEntity(medicoDTO);
        medico.setAtivo(true);
        repository.save(medico);
        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));
    }

    @GetMapping
    public ResponseEntity<Page<MedicoResumoDTO>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return ResponseEntity.ok().body(repository.findAllByAtivoTrue(paginacao).map(medico -> medicoAssembler.toResumoDTO(medico)));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid AtualizacaoMedicoDTO atualizacaoMedicoDTO) {
        var medico = repository.getReferenceById(atualizacaoMedicoDTO.getId());
        medico.atualizarInformacoes(atualizacaoMedicoDTO);
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }

    @DeleteMapping(value = "/{idMedico}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long idMedico) {
        var medico = repository.getReferenceById(idMedico);
        medico.excluir();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        Optional<Medico> optionalMedico = repository.findById(id);
        return ResponseEntity.ok(new DadosDetalhamentoMedico(optionalMedico.get()));
    }
}
