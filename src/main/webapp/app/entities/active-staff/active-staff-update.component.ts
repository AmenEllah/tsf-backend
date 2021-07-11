import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IActiveStaff, ActiveStaff } from 'app/shared/model/active-staff.model';
import { ActiveStaffService } from './active-staff.service';

@Component({
  selector: 'jhi-active-staff-update',
  templateUrl: './active-staff-update.component.html',
})
export class ActiveStaffUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    matricule: [null, [Validators.required]],
    idBu: [null, [Validators.required]],
    idRole: [null, [Validators.required]],
    email: [],
    idPoste: [null, [Validators.required]],
  });

  constructor(protected activeStaffService: ActiveStaffService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ activeStaff }) => {
      this.updateForm(activeStaff);
    });
  }

  updateForm(activeStaff: IActiveStaff): void {
    this.editForm.patchValue({
      id: activeStaff.id,
      matricule: activeStaff.matricule,
      idBu: activeStaff.idBu,
      idRole: activeStaff.idRole,
      email: activeStaff.email,
      idPoste: activeStaff.idPoste,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const activeStaff = this.createFromForm();
    if (activeStaff.id !== undefined) {
      this.subscribeToSaveResponse(this.activeStaffService.update(activeStaff));
    } else {
      this.subscribeToSaveResponse(this.activeStaffService.create(activeStaff));
    }
  }

  private createFromForm(): IActiveStaff {
    return {
      ...new ActiveStaff(),
      id: this.editForm.get(['id'])!.value,
      matricule: this.editForm.get(['matricule'])!.value,
      idBu: this.editForm.get(['idBu'])!.value,
      idRole: this.editForm.get(['idRole'])!.value,
      email: this.editForm.get(['email'])!.value,
      idPoste: this.editForm.get(['idPoste'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IActiveStaff>>): void {
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
