import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IStaffPermission, StaffPermission } from 'app/shared/model/staff-permission.model';
import { StaffPermissionService } from './staff-permission.service';

@Component({
  selector: 'jhi-staff-permission-update',
  templateUrl: './staff-permission-update.component.html',
})
export class StaffPermissionUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    idRole: [null, [Validators.required]],
    idBu: [null, [Validators.required]],
    action: [null, [Validators.required]],
    scopePermission: [null, [Validators.required]],
    staffType: [],
  });

  constructor(
    protected staffPermissionService: StaffPermissionService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ staffPermission }) => {
      this.updateForm(staffPermission);
    });
  }

  updateForm(staffPermission: IStaffPermission): void {
    this.editForm.patchValue({
      id: staffPermission.id,
      idRole: staffPermission.idRole,
      idBu: staffPermission.idBu,
      action: staffPermission.action,
      scopePermission: staffPermission.scopePermission,
      staffType: staffPermission.staffType,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const staffPermission = this.createFromForm();
    if (staffPermission.id !== undefined) {
      this.subscribeToSaveResponse(this.staffPermissionService.update(staffPermission));
    } else {
      this.subscribeToSaveResponse(this.staffPermissionService.create(staffPermission));
    }
  }

  private createFromForm(): IStaffPermission {
    return {
      ...new StaffPermission(),
      id: this.editForm.get(['id'])!.value,
      idRole: this.editForm.get(['idRole'])!.value,
      idBu: this.editForm.get(['idBu'])!.value,
      action: this.editForm.get(['action'])!.value,
      scopePermission: this.editForm.get(['scopePermission'])!.value,
      staffType: this.editForm.get(['staffType'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IStaffPermission>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
