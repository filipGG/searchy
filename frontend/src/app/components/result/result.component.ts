import { Component, Input } from '@angular/core';
import { ResultViewModel } from './result-view-model';

@Component({
  selector: 'app-result[viewModel]',
  templateUrl: './result.component.html',
  styleUrl: './result.component.css',
})
export class ResultComponent {
  @Input()
  public viewModel?: ResultViewModel | null;
}
