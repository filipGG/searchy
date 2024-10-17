import { Component } from '@angular/core';
import { SearchService } from '../../services/search/search.service';
import { catchError, debounceTime, finalize, map, of, switchMap } from 'rxjs';
import { FormControl } from '@angular/forms';
import { toResultViewModel } from '../result/result-view-model';

@Component({
  selector: 'app-search-result-counter',
  templateUrl: './search-result-counter.component.html',
  styleUrl: './search-result-counter.component.css',
})
export class SearchResultCounterComponent {
  public loading = false;
  public error?: string;

  public readonly searchControl = new FormControl<string>('');
  public readonly viewModel$ = this.searchControl.valueChanges.pipe(
    debounceTime(400),
    switchMap((query) => this.search(query)),
    map(toResultViewModel)
  );

  constructor(private _searchService: SearchService) {}

  private search(query: string | null) {
    this.error = undefined;
    this.loading = false;

    if (query == null || query.length === 0) {
      return of(undefined);
    }

    this.loading = true;
    return this._searchService.getSearchHitCounts(query).pipe(
      catchError(() => {
        this.error = 'Something went wrong, please try again!';
        return of(undefined);
      }),
      finalize(() => {
        this.loading = false;
      })
    );
  }
}
