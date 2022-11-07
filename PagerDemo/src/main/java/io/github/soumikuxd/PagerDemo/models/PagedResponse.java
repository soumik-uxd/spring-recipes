package io.github.soumikuxd.PagerDemo.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@JsonInclude(Include.NON_EMPTY)
public class PagedResponse {
    @Setter
    private String nextPage;
    private List<ResponseRow> items;
}
