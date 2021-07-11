import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRequiredDoc } from 'app/shared/model/required-doc.model';

@Component({
  selector: 'jhi-required-doc-detail',
  templateUrl: './required-doc-detail.component.html',
})
export class RequiredDocDetailComponent implements OnInit {
  requiredDoc: IRequiredDoc | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ requiredDoc }) => (this.requiredDoc = requiredDoc));
  }

  previousState(): void {
    window.history.back();
  }
}
