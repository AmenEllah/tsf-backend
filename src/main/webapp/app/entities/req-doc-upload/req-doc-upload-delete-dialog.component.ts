import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IReqDocUpload } from 'app/shared/model/req-doc-upload.model';
import { ReqDocUploadService } from './req-doc-upload.service';

@Component({
  templateUrl: './req-doc-upload-delete-dialog.component.html',
})
export class ReqDocUploadDeleteDialogComponent {
  reqDocUpload?: IReqDocUpload;

  constructor(
    protected reqDocUploadService: ReqDocUploadService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.reqDocUploadService.delete(id).subscribe(() => {
      this.eventManager.broadcast('reqDocUploadListModification');
      this.activeModal.close();
    });
  }
}
