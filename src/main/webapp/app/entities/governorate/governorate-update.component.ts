import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IGovernorate, Governorate } from 'app/shared/model/governorate.model';
import { GovernorateService } from './governorate.service';

@Component({
  selector: 'jhi-governorate-update',
  templateUrl: './governorate-update.component.html',
})
export class GovernorateUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    name: [],
  });

  constructor(protected governorateService: GovernorateService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ governorate }) => {
      this.updateForm(governorate);
    });
  }

  updateForm(governorate: IGovernorate): void {
    this.editForm.patchValue({
      id: governorate.id,
      name: governorate.name,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const governorate = this.createFromForm();
    if (governorate.id !== undefined) {
      this.subscribeToSaveResponse(this.governorateService.update(governorate));
    } else {
      this.subscribeToSaveResponse(this.governorateService.create(governorate));
    }
  }

  private createFromForm(): IGovernorate {
    return {
      ...new Governorate(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IGovernorate>>): void {
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
