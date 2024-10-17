import { Injectable } from '@angular/core';
import { Endpoint, HttpService } from '../http/http.service';
import { Observable } from 'rxjs';
import { SearchResponse } from './search-response';

@Injectable()
export class SearchService {
  constructor(private readonly _httpService: HttpService) {}

  public getSearchHitCounts(query: string): Observable<SearchResponse> {
    return this._httpService.get<SearchResponse>(Endpoint.search, {
      query,
    });
  }
}
