import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRequiredDocIncome } from 'app/shared/model/required-doc-income.model';
import { RequiredDocIncomeService } from './required-doc-income.service';

@Component({
  templateUrl: './required-doc-income-delete-dialog.component.html',
})
export class RequiredDocIncomeDeleteDialogComponent {
  requiredDocIncome?: IRequiredDocIncome;

  constructor(
    protected requiredDocIncomeService: RequiredDocIncomeService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.requiredDocIncomeService.delete(id).subscribe(() => {
      this.eventManager.broadcast('requiredDocIncomeListModification');
      this.activeModal.close();
    });
  }
}
