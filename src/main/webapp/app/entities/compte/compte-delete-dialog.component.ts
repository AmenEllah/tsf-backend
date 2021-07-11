import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICompte } from 'app/shared/model/compte.model';
import { CompteService } from './compte.service';

@Component({
  templateUrl: './compte-delete-dialog.component.html',
})
export class CompteDeleteDialogComponent {
  compte?: ICompte;

  constructor(protected compteService: CompteService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.compteService.delete(id).subscribe(() => {
      this.eventManager.broadcast('compteListModification');
      this.activeModal.close();
    });
  }
}
