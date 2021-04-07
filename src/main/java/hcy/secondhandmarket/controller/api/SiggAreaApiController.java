package hcy.secondhandmarket.controller.api;

import hcy.secondhandmarket.dto.siggarea.SiggAreaResponseDTO;
import hcy.secondhandmarket.service.siggarea.SiggAreaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sigg")
@RequiredArgsConstructor
@Slf4j
public class SiggAreaApiController {

    private final SiggAreaService siggAreaService;

    @GetMapping("/{sidoId}")
    public ResponseEntity<Result<SiggAreaResponseDTO>> getSiggList(@PathVariable("sidoId") Long sidoId) {
        List<SiggAreaResponseDTO> result = siggAreaService.findAllBySidoId(sidoId);
        return new ResponseEntity<>(new Result<>(result, result.size()), HttpStatus.OK);
    }
}


