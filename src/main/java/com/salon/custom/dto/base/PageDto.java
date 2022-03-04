package com.salon.custom.dto.base;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;

@Getter
@Setter
public class PageDto {
    private boolean last;
    private long totalElements;
    private int	totalPages;
    private int	size;
    private int	number;
    private Sort sort;
    private int	numberOfElements;
    private boolean	first;

}
