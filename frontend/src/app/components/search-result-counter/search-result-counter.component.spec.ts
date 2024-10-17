import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchResultCounterComponent } from './search-result-counter.component';

describe('SearchResultCounterComponent', () => {
  let component: SearchResultCounterComponent;
  let fixture: ComponentFixture<SearchResultCounterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SearchResultCounterComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SearchResultCounterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
