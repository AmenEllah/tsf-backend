import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IResidencyDocument } from 'app/shared/model/residency-document.model';

@Component({
  selector: 'jhi-residency-document-detail',
  templateUrl: './residency-document-detail.component.html',
})
export class ResidencyDocumentDetailComponent implements OnInit {
  residencyDocument: IResidencyDocument | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ residencyDocument }) => (this.residencyDocument = residencyDocument));
  }

  previousState(): void {
    window.history.back();
  }
}
