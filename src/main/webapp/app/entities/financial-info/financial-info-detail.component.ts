import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IFinancialInfo } from 'app/shared/model/financial-info.model';

@Component({
  selector: 'jhi-financial-info-detail',
  templateUrl: './financial-info-detail.component.html',
})
export class FinancialInfoDetailComponent implements OnInit {
  financialInfo: IFinancialInfo | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ financialInfo }) => (this.financialInfo = financialInfo));
  }

  previousState(): void {
    window.history.back();
  }
}
