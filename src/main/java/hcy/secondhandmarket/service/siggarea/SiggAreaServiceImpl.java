package hcy.secondhandmarket.service.siggarea;

import hcy.secondhandmarket.domain.SiggArea;
import hcy.secondhandmarket.dto.siggarea.SiggAreaResponseDTO;
import hcy.secondhandmarket.repository.siggarea.SiggAreaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SiggAreaServiceImpl implements SiggAreaService {

    private final SiggAreaRepository siggAreaRepository;

    @Override
    public List<SiggAreaResponseDTO> findAllBySidoId(Long sidoId) {
        List<SiggArea> result = siggAreaRepository.findAllBySidoId(sidoId);
        return result.stream().map(this::entityToDTO).collect(Collectors.toList());
    }
}
