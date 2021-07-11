import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISupplyMatrix } from 'app/shared/model/supply-matrix.model';

@Component({
  selector: 'jhi-supply-matrix-detail',
  templateUrl: './supply-matrix-detail.component.html',
})
export class SupplyMatrixDetailComponent implements OnInit {
  supplyMatrix: ISupplyMatrix | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ supplyMatrix }) => (this.supplyMatrix = supplyMatrix));
  }

  previousState(): void {
    window.history.back();
  }
}
