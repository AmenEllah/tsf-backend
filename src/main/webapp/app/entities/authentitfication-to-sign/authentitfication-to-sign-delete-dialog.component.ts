import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAuthentitficationToSign } from 'app/shared/model/authentitfication-to-sign.model';
import { AuthentitficationToSignService } from './authentitfication-to-sign.service';

@Component({
  templateUrl: './authentitfication-to-sign-delete-dialog.component.html',
})
export class AuthentitficationToSignDeleteDialogComponent {
  authentitficationToSign?: IAuthentitficationToSign;

  constructor(
    protected authentitficationToSignService: AuthentitficationToSignService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.authentitficationToSignService.delete(id).subscribe(() => {
      this.eventManager.broadcast('authentitficationToSignListModification');
      this.activeModal.close();
    });
  }
}
