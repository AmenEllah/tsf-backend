import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRequiredDocIncome } from 'app/shared/model/required-doc-income.model';

@Component({
  selector: 'jhi-required-doc-income-detail',
  templateUrl: './required-doc-income-detail.component.html',
})
export class RequiredDocIncomeDetailComponent implements OnInit {
  requiredDocIncome: IRequiredDocIncome | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ requiredDocIncome }) => (this.requiredDocIncome = requiredDocIncome));
  }

  previousState(): void {
    window.history.back();
  }
}
