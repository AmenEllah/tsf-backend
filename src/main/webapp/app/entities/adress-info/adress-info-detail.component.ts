import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAdressInfo } from 'app/shared/model/adress-info.model';

@Component({
  selector: 'jhi-adress-info-detail',
  templateUrl: './adress-info-detail.component.html',
})
export class AdressInfoDetailComponent implements OnInit {
  adressInfo: IAdressInfo | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ adressInfo }) => (this.adressInfo = adressInfo));
  }

  previousState(): void {
    window.history.back();
  }
}
