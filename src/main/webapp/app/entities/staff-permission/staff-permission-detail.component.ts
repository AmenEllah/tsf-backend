import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IStaffPermission } from 'app/shared/model/staff-permission.model';

@Component({
  selector: 'jhi-staff-permission-detail',
  templateUrl: './staff-permission-detail.component.html',
})
export class StaffPermissionDetailComponent implements OnInit {
  staffPermission: IStaffPermission | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ staffPermission }) => (this.staffPermission = staffPermission));
  }

  previousState(): void {
    window.history.back();
  }
}
