import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDerogation } from 'app/shared/model/derogation.model';
import { DerogationService } from './derogation.service';

@Component({
  templateUrl: './derogation-delete-dialog.component.html',
})
export class DerogationDeleteDialogComponent {
  derogation?: IDerogation;

  constructor(
    protected derogationService: DerogationService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.derogationService.delete(id).subscribe(() => {
      this.eventManager.broadcast('derogationListModification');
      this.activeModal.close();
    });
  }
}
