package hcy.secondhandmarket.service.sidoarea;

import hcy.secondhandmarket.domain.SidoArea;
import hcy.secondhandmarket.dto.sidoarea.SidoAreaResponseDTO;

import java.util.List;

public interface SidoAreaService {

    List<SidoAreaResponseDTO> findAll();

    default SidoAreaResponseDTO entityToDTO(SidoArea sidoArea) {
        return SidoAreaResponseDTO.builder().id(sidoArea.getId()).name(sidoArea.getName()).build();
    }

}
