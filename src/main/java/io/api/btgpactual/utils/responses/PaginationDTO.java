package io.api.btgpactual.utils.responses;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PaginationDTO {
    @JsonIgnore
    protected Long total;
}
