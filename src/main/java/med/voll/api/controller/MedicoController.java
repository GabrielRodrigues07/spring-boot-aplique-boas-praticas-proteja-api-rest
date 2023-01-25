package med.voll.api.controller;

import lombok.AllArgsConstructor;
import med.voll.api.assembler.MedicoAssembler;
import med.voll.api.dto.AtualizacaoMedicoDTO;
import med.voll.api.dto.MedicoDTO;
import med.voll.api.dto.MedicoResumoDTO;
import med.voll.api.medico.Medico;
import med.voll.api.medico.MedicoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/medicos")
@AllArgsConstructor
public class MedicoController {

    private MedicoRepository repository;

    private MedicoAssembler medicoAssembler;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid MedicoDTO medicoDTO) {
        Medico medico = medicoAssembler.toEntity(medicoDTO);
        medico.setAtivo(true);
        repository.save(medico);
    }

    @GetMapping
    public Page<MedicoResumoDTO> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao).map(medico -> medicoAssembler.toResumoDTO(medico));
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid AtualizacaoMedicoDTO atualizacaoMedicoDTO) {
        var medico = repository.getReferenceById(atualizacaoMedicoDTO.getId());
        medico.atualizarInformacoes(atualizacaoMedicoDTO);
    }

    @DeleteMapping(value = "/{idMedico}")
    @Transactional
    public void excluir(@PathVariable Long idMedico) {
        var medico = repository.getReferenceById(idMedico);
        medico.excluir();
    }
}
