import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDetailOffer } from 'app/shared/model/detail-offer.model';

@Component({
  selector: 'jhi-detail-offer-detail',
  templateUrl: './detail-offer-detail.component.html',
})
export class DetailOfferDetailComponent implements OnInit {
  detailOffer: IDetailOffer | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ detailOffer }) => (this.detailOffer = detailOffer));
  }

  previousState(): void {
    window.history.back();
  }
}
