package med.voll.api.assembler;

import lombok.AllArgsConstructor;
import med.voll.api.dto.PacienteDTO;
import med.voll.api.dto.PacienteResumoDTO;
import med.voll.api.paciente.Paciente;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class PacienteAssembler {

    private ModelMapper modelMapper;

    public Paciente toEntity(PacienteDTO pacienteDTO) {
        return modelMapper.map(pacienteDTO, Paciente.class);
    }

    public PacienteResumoDTO toResumoDTO(Paciente paciente) {
        return modelMapper.map(paciente, PacienteResumoDTO.class);
    }
}
