import {
  SearchEngineQuery,
  SearchResponse,
  SingleQueryResult,
} from '../../services/search/search-response';

export interface ResultViewModel {
  searchEngineResults: SearchEngineResult[];
}

export interface SearchEngineResult {
  name: string;
  totalHits: number;
  queries: QueryResult[];
}

export interface QueryResult {
  query: string;
  hits: number;
  error: string | null;
}

export function toResultViewModel(
  response?: SearchResponse
): ResultViewModel | undefined {
  if (!response) {
    return undefined;
  }

  return {
    searchEngineResults: response.results.map(toSearchEngineResult),
  };
}

function toSearchEngineResult(response: SearchEngineQuery): SearchEngineResult {
  return {
    name: response.searchEngineName,
    totalHits: sumHits(response.queryResults),
    queries: response.queryResults.map(toQueryResult),
  };
}

function toQueryResult(response: SingleQueryResult): QueryResult {
  return {
    ...response,
  };
}

function sumHits(queryResults: SingleQueryResult[]) {
  return queryResults.reduce((prev, curr) => prev + curr.hits, 0);
}
