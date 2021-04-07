package hcy.secondhandmarket.service.emdarea;

import hcy.secondhandmarket.domain.EmdArea;
import hcy.secondhandmarket.dto.emdarea.EmdAreaResponseDTO;
import hcy.secondhandmarket.repository.emdarea.EmdAreaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmdAreaServiceImpl implements EmdAreaService{

    private final EmdAreaRepository emdAreaRepository;

    @Override
    public List<EmdAreaResponseDTO> findAllBySiggId(Long siggId) {
        List<EmdArea> result = emdAreaRepository.findAllBySiggId(siggId);
        return result.stream().map(this::entityToDTO).collect(Collectors.toList());
    }

}
