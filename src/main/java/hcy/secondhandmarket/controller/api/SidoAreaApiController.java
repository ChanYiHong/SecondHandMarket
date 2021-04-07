package hcy.secondhandmarket.controller.api;

import hcy.secondhandmarket.dto.sidoarea.SidoAreaResponseDTO;
import hcy.secondhandmarket.service.sidoarea.SidoAreaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sido")
@RequiredArgsConstructor
@Slf4j
public class SidoAreaApiController {

    private final SidoAreaService sidoAreaService;

    @GetMapping
    public ResponseEntity<Result<SidoAreaResponseDTO>> getSidoList() {
        List<SidoAreaResponseDTO> result = sidoAreaService.findAll();
        return new ResponseEntity<>(new Result<>(result, result.size()), HttpStatus.OK);
    }

}
