package io.github.soumikuxd.pagerhazelcastdemo.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

import java.util.List;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PagedResponse {
    @Setter
    private String nextPage;
    private List<ResponseRow> items;
}
