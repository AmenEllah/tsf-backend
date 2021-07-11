import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IOtherRevenuFile } from 'app/shared/model/other-revenu-file.model';
import { OtherRevenuFileService } from './other-revenu-file.service';

@Component({
  templateUrl: './other-revenu-file-delete-dialog.component.html',
})
export class OtherRevenuFileDeleteDialogComponent {
  otherRevenuFile?: IOtherRevenuFile;

  constructor(
    protected otherRevenuFileService: OtherRevenuFileService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.otherRevenuFileService.delete(id).subscribe(() => {
      this.eventManager.broadcast('otherRevenuFileListModification');
      this.activeModal.close();
    });
  }
}
