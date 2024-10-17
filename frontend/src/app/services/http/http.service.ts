import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../environment/environment';

export enum Endpoint {
  search = '/search',
}

@Injectable()
export class HttpService {
  constructor(private _httpClient: HttpClient) {}

  public get<T>(endpoint: Endpoint, params: { [key: string]: any }) {
    const url = this.buildUrl(endpoint);
    return this._httpClient.get<T>(url, { params });
  }

  private buildUrl(endpoint: Endpoint) {
    const { API_URL } = environment;
    return API_URL + endpoint;
  }
}
