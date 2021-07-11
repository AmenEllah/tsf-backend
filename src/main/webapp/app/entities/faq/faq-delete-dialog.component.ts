import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IFAQ } from 'app/shared/model/faq.model';
import { FAQService } from './faq.service';

@Component({
  templateUrl: './faq-delete-dialog.component.html',
})
export class FAQDeleteDialogComponent {
  fAQ?: IFAQ;

  constructor(protected fAQService: FAQService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.fAQService.delete(id).subscribe(() => {
      this.eventManager.broadcast('fAQListModification');
      this.activeModal.close();
    });
  }
}
