import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMonthlyNetIncome } from 'app/shared/model/monthly-net-income.model';

@Component({
  selector: 'jhi-monthly-net-income-detail',
  templateUrl: './monthly-net-income-detail.component.html',
})
export class MonthlyNetIncomeDetailComponent implements OnInit {
  monthlyNetIncome: IMonthlyNetIncome | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ monthlyNetIncome }) => (this.monthlyNetIncome = monthlyNetIncome));
  }

  previousState(): void {
    window.history.back();
  }
}
