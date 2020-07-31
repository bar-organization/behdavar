package com.bar.behdavarbackend.util.pagination;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PagingRequest {
    int start;
    int max;
    List<SearchCriteria> filters = new ArrayList<>();
    SortOperation sort;
}
