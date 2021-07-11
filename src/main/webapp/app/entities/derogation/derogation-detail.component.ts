import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDerogation } from 'app/shared/model/derogation.model';

@Component({
  selector: 'jhi-derogation-detail',
  templateUrl: './derogation-detail.component.html',
})
export class DerogationDetailComponent implements OnInit {
  derogation: IDerogation | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ derogation }) => (this.derogation = derogation));
  }

  previousState(): void {
    window.history.back();
  }
}
