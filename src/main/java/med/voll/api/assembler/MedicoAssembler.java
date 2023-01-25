package med.voll.api.assembler;

import lombok.AllArgsConstructor;
import med.voll.api.dto.AtualizacaoMedicoDTO;
import med.voll.api.dto.MedicoDTO;
import med.voll.api.dto.MedicoResumoDTO;
import med.voll.api.domain.medico.Medico;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class MedicoAssembler {

    private ModelMapper modelMapper;

    public Medico toEntity(MedicoDTO medicoDTO) {
        return modelMapper.map(medicoDTO, Medico.class);
    }

    public Medico toModel(AtualizacaoMedicoDTO medicoDTO) {
        return modelMapper.map(medicoDTO, Medico.class);
    }

    public MedicoResumoDTO toResumoDTO(Medico medico) {
        return modelMapper.map(medico, MedicoResumoDTO.class);
    }

//    public AtualizacaoMedicoDTO toUpdateMedicoDTO(Medico dados) {
//        if (dados.nome() != null) {
//            this.nome = dados.nome();
//        }
//        if (dados.telefone() != null) {
//            this.telefone = dados.telefone();
//        }
//        if (dados.endereco() != null) {
//            this.endereco.atualizarInformacoes(dados.endereco());
//        }
//    }
}
