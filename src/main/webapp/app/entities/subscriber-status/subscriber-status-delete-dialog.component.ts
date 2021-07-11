import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISubscriberStatus } from 'app/shared/model/subscriber-status.model';
import { SubscriberStatusService } from './subscriber-status.service';

@Component({
  templateUrl: './subscriber-status-delete-dialog.component.html',
})
export class SubscriberStatusDeleteDialogComponent {
  subscriberStatus?: ISubscriberStatus;

  constructor(
    protected subscriberStatusService: SubscriberStatusService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.subscriberStatusService.delete(id).subscribe(() => {
      this.eventManager.broadcast('subscriberStatusListModification');
      this.activeModal.close();
    });
  }
}
