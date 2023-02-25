package com.damnjan.nystores.model.responseModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResponseModel<T> {

    private List<T> response;
    private Integer totalPages;
    private Integer currentPage;
    private Long totalElements;
}
