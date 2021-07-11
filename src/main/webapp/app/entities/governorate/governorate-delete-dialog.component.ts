import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IGovernorate } from 'app/shared/model/governorate.model';
import { GovernorateService } from './governorate.service';

@Component({
  templateUrl: './governorate-delete-dialog.component.html',
})
export class GovernorateDeleteDialogComponent {
  governorate?: IGovernorate;

  constructor(
    protected governorateService: GovernorateService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.governorateService.delete(id).subscribe(() => {
      this.eventManager.broadcast('governorateListModification');
      this.activeModal.close();
    });
  }
}
