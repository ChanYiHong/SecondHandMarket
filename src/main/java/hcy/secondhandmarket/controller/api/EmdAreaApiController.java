package hcy.secondhandmarket.controller.api;

import hcy.secondhandmarket.dto.emdarea.EmdAreaResponseDTO;
import hcy.secondhandmarket.dto.siggarea.SiggAreaResponseDTO;
import hcy.secondhandmarket.service.emdarea.EmdAreaService;
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
@RequestMapping("/emd")
@RequiredArgsConstructor
@Slf4j
public class EmdAreaApiController {

    private final EmdAreaService emdAreaService;

    @GetMapping("/{siggId}")
    public ResponseEntity<Result<EmdAreaResponseDTO>> getSiggList(@PathVariable("siggId") Long siggId) {
        List<EmdAreaResponseDTO> result = emdAreaService.findAllBySiggId(siggId);
        return new ResponseEntity<>(new Result<>(result, result.size()), HttpStatus.OK);
    }

}
