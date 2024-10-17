import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { provideHttpClient } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { HttpService } from './services/http/http.service';
import { SearchResultCounterComponent } from './components/search-result-counter/search-result-counter.component';
import { SearchService } from './services/search/search.service';
import { ResultComponent } from './components/result/result.component';

@NgModule({
  declarations: [AppComponent, SearchResultCounterComponent, ResultComponent],
  imports: [BrowserModule, ReactiveFormsModule],
  providers: [provideHttpClient(), HttpService, HttpService, SearchService],
  bootstrap: [AppComponent],
})
export class AppModule {}
