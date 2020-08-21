package com.bar.behdavarbackend.util.pagination;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PagingResponse {
    private int start;
    private int max;
    private long total;
    Object data;
}
