export class PagingRequest {
  start: number;
  max: number;
  filters: SearchCriteria[];
  sort: SortOperation;
}

export class PagingResponse<T> {
  start: number;
  max: number;
  total: number;
  data: T[];
}

export class SearchCriteria {
  key: string;
  value: any;
  operation: SearchOperation;
}

export enum SortDirectionEnum {
  ASC = 'ASC',
  DESC = 'DESC',
}

export class SortOperation {
  direction: SortDirectionEnum;
  sortBy: string;
}

export enum SearchOperation {
  GREATER_THAN = "GREATER_THAN",
  LESS_THAN = "LESS_THAN",
  GREATER_THAN_EQUAL = "GREATER_THAN_EQUAL",
  LESS_THAN_EQUAL = "LESS_THAN_EQUAL",
  NOT_EQUAL = "NOT_EQUAL",
  EQUAL = "EQUAL",
  MATCH = "MATCH",
  MATCH_START = "MATCH_START",
  MATCH_END = "MATCH_END",
  IN = "IN",
  NOT_IN = "NOT_IN"
}

