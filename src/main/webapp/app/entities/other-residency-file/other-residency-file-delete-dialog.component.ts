import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IOtherResidencyFile } from 'app/shared/model/other-residency-file.model';
import { OtherResidencyFileService } from './other-residency-file.service';

@Component({
  templateUrl: './other-residency-file-delete-dialog.component.html',
})
export class OtherResidencyFileDeleteDialogComponent {
  otherResidencyFile?: IOtherResidencyFile;

  constructor(
    protected otherResidencyFileService: OtherResidencyFileService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.otherResidencyFileService.delete(id).subscribe(() => {
      this.eventManager.broadcast('otherResidencyFileListModification');
      this.activeModal.close();
    });
  }
}
