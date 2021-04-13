package hcy.secondhandmarket.service.emdarea;

import hcy.secondhandmarket.domain.EmdArea;
import hcy.secondhandmarket.dto.emdarea.EmdAreaResponseDTO;

import java.util.List;

public interface EmdAreaService {

    List<EmdAreaResponseDTO> findAllBySiggId(Long siggId);

    default EmdAreaResponseDTO entityToDTO(EmdArea emdArea) {
        return EmdAreaResponseDTO.builder().id(emdArea.getId()).name(emdArea.getName()).build();
    }

}
