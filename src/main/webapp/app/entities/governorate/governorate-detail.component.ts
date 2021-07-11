import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IGovernorate } from 'app/shared/model/governorate.model';

@Component({
  selector: 'jhi-governorate-detail',
  templateUrl: './governorate-detail.component.html',
})
export class GovernorateDetailComponent implements OnInit {
  governorate: IGovernorate | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ governorate }) => (this.governorate = governorate));
  }

  previousState(): void {
    window.history.back();
  }
}
