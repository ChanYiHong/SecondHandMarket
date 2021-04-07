package hcy.secondhandmarket.service.sidoarea;

import hcy.secondhandmarket.domain.SidoArea;
import hcy.secondhandmarket.dto.sidoarea.SidoAreaResponseDTO;
import hcy.secondhandmarket.repository.sidoarea.SidoAreaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SidoAreaServiceImpl implements SidoAreaService{

    private final SidoAreaRepository sidoAreaRepository;

    @Override
    public List<SidoAreaResponseDTO> findAll() {
        List<SidoArea> result = sidoAreaRepository.findAll();
        return result.stream().map(this::entityToDTO).collect(Collectors.toList());
    }
}
