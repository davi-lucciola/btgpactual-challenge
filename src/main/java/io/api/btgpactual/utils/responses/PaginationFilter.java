package io.api.btgpactual.utils.responses;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaginationFilter {
    protected Integer page = 1;
    protected Integer pageSize = 10;
}
