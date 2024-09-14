package io.api.btgpactual.utils.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaginationResponse<T> {
    private List<T> data;
    private Long total;
    private Integer page;
    private Integer pageSize;
}
