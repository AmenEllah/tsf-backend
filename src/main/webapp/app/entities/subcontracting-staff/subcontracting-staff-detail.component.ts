import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISubcontractingStaff } from 'app/shared/model/subcontracting-staff.model';

@Component({
  selector: 'jhi-subcontracting-staff-detail',
  templateUrl: './subcontracting-staff-detail.component.html',
})
export class SubcontractingStaffDetailComponent implements OnInit {
  subcontractingStaff: ISubcontractingStaff | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ subcontractingStaff }) => (this.subcontractingStaff = subcontractingStaff));
  }

  previousState(): void {
    window.history.back();
  }
}
