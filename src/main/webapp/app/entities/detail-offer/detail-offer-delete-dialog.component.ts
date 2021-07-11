import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDetailOffer } from 'app/shared/model/detail-offer.model';
import { DetailOfferService } from './detail-offer.service';

@Component({
  templateUrl: './detail-offer-delete-dialog.component.html',
})
export class DetailOfferDeleteDialogComponent {
  detailOffer?: IDetailOffer;

  constructor(
    protected detailOfferService: DetailOfferService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.detailOfferService.delete(id).subscribe(() => {
      this.eventManager.broadcast('detailOfferListModification');
      this.activeModal.close();
    });
  }
}
