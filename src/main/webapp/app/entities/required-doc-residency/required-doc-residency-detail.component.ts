import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRequiredDocResidency } from 'app/shared/model/required-doc-residency.model';

@Component({
  selector: 'jhi-required-doc-residency-detail',
  templateUrl: './required-doc-residency-detail.component.html',
})
export class RequiredDocResidencyDetailComponent implements OnInit {
  requiredDocResidency: IRequiredDocResidency | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ requiredDocResidency }) => (this.requiredDocResidency = requiredDocResidency));
  }

  previousState(): void {
    window.history.back();
  }
}
