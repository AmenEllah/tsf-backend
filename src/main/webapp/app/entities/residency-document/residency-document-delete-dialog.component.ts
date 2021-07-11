import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IResidencyDocument } from 'app/shared/model/residency-document.model';
import { ResidencyDocumentService } from './residency-document.service';

@Component({
  templateUrl: './residency-document-delete-dialog.component.html',
})
export class ResidencyDocumentDeleteDialogComponent {
  residencyDocument?: IResidencyDocument;

  constructor(
    protected residencyDocumentService: ResidencyDocumentService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.residencyDocumentService.delete(id).subscribe(() => {
      this.eventManager.broadcast('residencyDocumentListModification');
      this.activeModal.close();
    });
  }
}
