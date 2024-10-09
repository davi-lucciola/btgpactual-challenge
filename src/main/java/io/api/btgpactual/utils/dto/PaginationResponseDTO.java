package io.api.btgpactual.utils.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaginationResponseDTO<T> {
    private List<T> data;
    private Long total;
    private Integer page;
    private Integer pageSize;
}
