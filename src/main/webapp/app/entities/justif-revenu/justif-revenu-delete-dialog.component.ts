import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IJustifRevenu } from 'app/shared/model/justif-revenu.model';
import { JustifRevenuService } from './justif-revenu.service';

@Component({
  templateUrl: './justif-revenu-delete-dialog.component.html',
})
export class JustifRevenuDeleteDialogComponent {
  justifRevenu?: IJustifRevenu;

  constructor(
    protected justifRevenuService: JustifRevenuService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.justifRevenuService.delete(id).subscribe(() => {
      this.eventManager.broadcast('justifRevenuListModification');
      this.activeModal.close();
    });
  }
}
