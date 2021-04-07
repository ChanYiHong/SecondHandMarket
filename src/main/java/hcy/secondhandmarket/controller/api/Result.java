package hcy.secondhandmarket.controller.api;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Result<T> {

    private List<T> data;
    private int size;

}
