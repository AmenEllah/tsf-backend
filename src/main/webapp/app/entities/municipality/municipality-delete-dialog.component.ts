import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMunicipality } from 'app/shared/model/municipality.model';
import { MunicipalityService } from './municipality.service';

@Component({
  templateUrl: './municipality-delete-dialog.component.html',
})
export class MunicipalityDeleteDialogComponent {
  municipality?: IMunicipality;

  constructor(
    protected municipalityService: MunicipalityService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.municipalityService.delete(id).subscribe(() => {
      this.eventManager.broadcast('municipalityListModification');
      this.activeModal.close();
    });
  }
}
