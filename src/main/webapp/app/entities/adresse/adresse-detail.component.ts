import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAdresse } from 'app/shared/model/adresse.model';

@Component({
  selector: 'jhi-adresse-detail',
  templateUrl: './adresse-detail.component.html',
})
export class AdresseDetailComponent implements OnInit {
  adresse: IAdresse | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ adresse }) => (this.adresse = adresse));
  }

  previousState(): void {
    window.history.back();
  }
}
