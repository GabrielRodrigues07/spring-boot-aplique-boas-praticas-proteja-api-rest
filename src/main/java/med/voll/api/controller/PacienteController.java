package med.voll.api.controller;

import lombok.AllArgsConstructor;
import med.voll.api.assembler.PacienteAssembler;
import med.voll.api.dto.AtualizacaoPacienteDTO;
import med.voll.api.dto.PacienteDTO;
import med.voll.api.dto.PacienteResumoDTO;
import med.voll.api.paciente.Paciente;
import med.voll.api.paciente.PacienteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/pacientes")
@AllArgsConstructor
public class PacienteController {

    private PacienteRepository repository;
    private PacienteAssembler pacienteAssembler;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid PacienteDTO pacienteDTO) {
        Paciente paciente = pacienteAssembler.toEntity(pacienteDTO);
        repository.save(paciente);
    }

    @GetMapping
    public Page<PacienteResumoDTO> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao).map(paciente -> pacienteAssembler.toResumoDTO(paciente));
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid AtualizacaoPacienteDTO atualizacaoPacienteDTO) {
        var paciente = repository.getReferenceById(atualizacaoPacienteDTO.getId());
        paciente.atualizarInformacoes(atualizacaoPacienteDTO);
    }

    @DeleteMapping(value = "/{idPaciente}")
    @Transactional
    public void excluir(@PathVariable Long idPaciente) {
        var paciente = repository.getReferenceById(idPaciente);
        paciente.excluir();
    }
}
