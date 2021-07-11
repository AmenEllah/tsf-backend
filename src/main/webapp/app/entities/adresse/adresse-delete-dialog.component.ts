import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAdresse } from 'app/shared/model/adresse.model';
import { AdresseService } from './adresse.service';

@Component({
  templateUrl: './adresse-delete-dialog.component.html',
})
export class AdresseDeleteDialogComponent {
  adresse?: IAdresse;

  constructor(protected adresseService: AdresseService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.adresseService.delete(id).subscribe(() => {
      this.eventManager.broadcast('adresseListModification');
      this.activeModal.close();
    });
  }
}
