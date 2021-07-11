import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IActiveStaff } from 'app/shared/model/active-staff.model';

@Component({
  selector: 'jhi-active-staff-detail',
  templateUrl: './active-staff-detail.component.html',
})
export class ActiveStaffDetailComponent implements OnInit {
  activeStaff: IActiveStaff | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ activeStaff }) => (this.activeStaff = activeStaff));
  }

  previousState(): void {
    window.history.back();
  }
}
