export interface SearchResponse {
  results: SearchEngineQuery[];
}

export interface SearchEngineQuery {
  searchEngineName: string;
  queryResults: SingleQueryResult[];
}

export interface SingleQueryResult {
  query: string;
  hits: number;
  error: string | null;
}
