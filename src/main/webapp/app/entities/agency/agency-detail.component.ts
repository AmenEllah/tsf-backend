import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAgency } from 'app/shared/model/agency.model';

@Component({
  selector: 'jhi-agency-detail',
  templateUrl: './agency-detail.component.html',
})
export class AgencyDetailComponent implements OnInit {
  agency: IAgency | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ agency }) => (this.agency = agency));
  }

  previousState(): void {
    window.history.back();
  }
}
