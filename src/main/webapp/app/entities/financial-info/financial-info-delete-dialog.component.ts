import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IFinancialInfo } from 'app/shared/model/financial-info.model';
import { FinancialInfoService } from './financial-info.service';

@Component({
  templateUrl: './financial-info-delete-dialog.component.html',
})
export class FinancialInfoDeleteDialogComponent {
  financialInfo?: IFinancialInfo;

  constructor(
    protected financialInfoService: FinancialInfoService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.financialInfoService.delete(id).subscribe(() => {
      this.eventManager.broadcast('financialInfoListModification');
      this.activeModal.close();
    });
  }
}
