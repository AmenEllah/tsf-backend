import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMunicipality } from 'app/shared/model/municipality.model';

@Component({
  selector: 'jhi-municipality-detail',
  templateUrl: './municipality-detail.component.html',
})
export class MunicipalityDetailComponent implements OnInit {
  municipality: IMunicipality | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ municipality }) => (this.municipality = municipality));
  }

  previousState(): void {
    window.history.back();
  }
}
