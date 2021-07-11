import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { INationality } from 'app/shared/model/nationality.model';
import { NationalityService } from './nationality.service';

@Component({
  templateUrl: './nationality-delete-dialog.component.html',
})
export class NationalityDeleteDialogComponent {
  nationality?: INationality;

  constructor(
    protected nationalityService: NationalityService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.nationalityService.delete(id).subscribe(() => {
      this.eventManager.broadcast('nationalityListModification');
      this.activeModal.close();
    });
  }
}
