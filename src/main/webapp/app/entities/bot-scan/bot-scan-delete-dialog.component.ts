import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBotScan } from 'app/shared/model/bot-scan.model';
import { BotScanService } from './bot-scan.service';

@Component({
  templateUrl: './bot-scan-delete-dialog.component.html',
})
export class BotScanDeleteDialogComponent {
  botScan?: IBotScan;

  constructor(protected botScanService: BotScanService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.botScanService.delete(id).subscribe(() => {
      this.eventManager.broadcast('botScanListModification');
      this.activeModal.close();
    });
  }
}
