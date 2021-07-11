import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMonthlyNetIncome } from 'app/shared/model/monthly-net-income.model';
import { MonthlyNetIncomeService } from './monthly-net-income.service';

@Component({
  templateUrl: './monthly-net-income-delete-dialog.component.html',
})
export class MonthlyNetIncomeDeleteDialogComponent {
  monthlyNetIncome?: IMonthlyNetIncome;

  constructor(
    protected monthlyNetIncomeService: MonthlyNetIncomeService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.monthlyNetIncomeService.delete(id).subscribe(() => {
      this.eventManager.broadcast('monthlyNetIncomeListModification');
      this.activeModal.close();
    });
  }
}
