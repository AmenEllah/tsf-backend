import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRequiredDocResidency } from 'app/shared/model/required-doc-residency.model';
import { RequiredDocResidencyService } from './required-doc-residency.service';

@Component({
  templateUrl: './required-doc-residency-delete-dialog.component.html',
})
export class RequiredDocResidencyDeleteDialogComponent {
  requiredDocResidency?: IRequiredDocResidency;

  constructor(
    protected requiredDocResidencyService: RequiredDocResidencyService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.requiredDocResidencyService.delete(id).subscribe(() => {
      this.eventManager.broadcast('requiredDocResidencyListModification');
      this.activeModal.close();
    });
  }
}
