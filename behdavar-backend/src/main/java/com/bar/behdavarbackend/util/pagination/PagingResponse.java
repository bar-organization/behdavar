package com.bar.behdavarbackend.util.pagination;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PagingResponse<E> {
    private int start;
    private int max;
    private long total;
    List<E> data;
}
