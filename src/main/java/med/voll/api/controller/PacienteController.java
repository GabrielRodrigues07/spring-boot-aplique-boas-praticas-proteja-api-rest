package med.voll.api.controller;

import lombok.AllArgsConstructor;
import med.voll.api.assembler.PacienteAssembler;
import med.voll.api.dto.AtualizacaoPacienteDTO;
import med.voll.api.dto.PacienteDTO;
import med.voll.api.dto.PacienteResumoDTO;
import med.voll.api.paciente.DadosDetalhamentoPaciente;
import med.voll.api.paciente.Paciente;
import med.voll.api.paciente.PacienteRepository;
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
@RequestMapping("/pacientes")
@AllArgsConstructor
public class PacienteController {

    private PacienteRepository repository;
    private PacienteAssembler pacienteAssembler;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid PacienteDTO pacienteDTO, UriComponentsBuilder uriBuilder) {
        Paciente paciente = pacienteAssembler.toEntity(pacienteDTO);
        repository.save(paciente);
        paciente.setAtivo(true);
        var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoPaciente(paciente));
    }

    @GetMapping
    public ResponseEntity<Page<PacienteResumoDTO>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        Page<PacienteResumoDTO> pacientes = repository.findAllByAtivoTrue(paginacao).map(paciente -> pacienteAssembler.toResumoDTO(paciente));
        return ResponseEntity.ok(pacientes);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid AtualizacaoPacienteDTO atualizacaoPacienteDTO) {
        var paciente = repository.getReferenceById(atualizacaoPacienteDTO.getId());
        paciente.atualizarInformacoes(atualizacaoPacienteDTO);
        return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
    }

    @DeleteMapping(value = "/{idPaciente}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long idPaciente) {
        var paciente = repository.getReferenceById(idPaciente);
        paciente.excluir();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        Optional<Paciente> optionalPaciente = repository.findById(id);
        return ResponseEntity.ok(new DadosDetalhamentoPaciente(optionalPaciente.get()));
    }
}
