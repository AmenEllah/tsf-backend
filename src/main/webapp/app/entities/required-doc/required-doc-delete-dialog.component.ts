import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRequiredDoc } from 'app/shared/model/required-doc.model';
import { RequiredDocService } from './required-doc.service';

@Component({
  templateUrl: './required-doc-delete-dialog.component.html',
})
export class RequiredDocDeleteDialogComponent {
  requiredDoc?: IRequiredDoc;

  constructor(
    protected requiredDocService: RequiredDocService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.requiredDocService.delete(id).subscribe(() => {
      this.eventManager.broadcast('requiredDocListModification');
      this.activeModal.close();
    });
  }
}
