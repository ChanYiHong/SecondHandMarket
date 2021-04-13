package hcy.secondhandmarket.service.siggarea;

import hcy.secondhandmarket.domain.SiggArea;
import hcy.secondhandmarket.dto.siggarea.SiggAreaResponseDTO;

import java.util.List;

public interface SiggAreaService {

    List<SiggAreaResponseDTO> findAllBySidoId(Long sidoId);

    default SiggAreaResponseDTO entityToDTO(SiggArea siggArea) {
        return SiggAreaResponseDTO.builder().id(siggArea.getId()).name(siggArea.getName()).build();
    }

}
