import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ISubcontractingStaff, SubcontractingStaff } from 'app/shared/model/subcontracting-staff.model';
import { SubcontractingStaffService } from './subcontracting-staff.service';

@Component({
  selector: 'jhi-subcontracting-staff-update',
  templateUrl: './subcontracting-staff-update.component.html',
})
export class SubcontractingStaffUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    matricule: [null, [Validators.required]],
    idBu: [null, [Validators.required]],
    idRole: [null, [Validators.required]],
    email: [],
    idPoste: [],
  });

  constructor(
    protected subcontractingStaffService: SubcontractingStaffService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ subcontractingStaff }) => {
      this.updateForm(subcontractingStaff);
    });
  }

  updateForm(subcontractingStaff: ISubcontractingStaff): void {
    this.editForm.patchValue({
      id: subcontractingStaff.id,
      matricule: subcontractingStaff.matricule,
      idBu: subcontractingStaff.idBu,
      idRole: subcontractingStaff.idRole,
      email: subcontractingStaff.email,
      idPoste: subcontractingStaff.idPoste,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const subcontractingStaff = this.createFromForm();
    if (subcontractingStaff.id !== undefined) {
      this.subscribeToSaveResponse(this.subcontractingStaffService.update(subcontractingStaff));
    } else {
      this.subscribeToSaveResponse(this.subcontractingStaffService.create(subcontractingStaff));
    }
  }

  private createFromForm(): ISubcontractingStaff {
    return {
      ...new SubcontractingStaff(),
      id: this.editForm.get(['id'])!.value,
      matricule: this.editForm.get(['matricule'])!.value,
      idBu: this.editForm.get(['idBu'])!.value,
      idRole: this.editForm.get(['idRole'])!.value,
      email: this.editForm.get(['email'])!.value,
      idPoste: this.editForm.get(['idPoste'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISubcontractingStaff>>): void {
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
