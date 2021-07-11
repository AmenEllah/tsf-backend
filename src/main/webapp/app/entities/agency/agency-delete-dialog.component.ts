import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAgency } from 'app/shared/model/agency.model';
import { AgencyService } from './agency.service';

@Component({
  templateUrl: './agency-delete-dialog.component.html',
})
export class AgencyDeleteDialogComponent {
  agency?: IAgency;

  constructor(protected agencyService: AgencyService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.agencyService.delete(id).subscribe(() => {
      this.eventManager.broadcast('agencyListModification');
      this.activeModal.close();
    });
  }
}
