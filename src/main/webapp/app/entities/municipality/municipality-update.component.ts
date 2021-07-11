import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IMunicipality, Municipality } from 'app/shared/model/municipality.model';
import { MunicipalityService } from './municipality.service';
import { IGovernorate } from 'app/shared/model/governorate.model';
import { GovernorateService } from 'app/entities/governorate/governorate.service';

@Component({
  selector: 'jhi-municipality-update',
  templateUrl: './municipality-update.component.html',
})
export class MunicipalityUpdateComponent implements OnInit {
  isSaving = false;
  governorates: IGovernorate[] = [];

  editForm = this.fb.group({
    id: [],
    name: [],
    governorateId: [],
  });

  constructor(
    protected municipalityService: MunicipalityService,
    protected governorateService: GovernorateService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ municipality }) => {
      this.updateForm(municipality);

      this.governorateService.query().subscribe((res: HttpResponse<IGovernorate[]>) => (this.governorates = res.body || []));
    });
  }

  updateForm(municipality: IMunicipality): void {
    this.editForm.patchValue({
      id: municipality.id,
      name: municipality.name,
      governorateId: municipality.governorateId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const municipality = this.createFromForm();
    if (municipality.id !== undefined) {
      this.subscribeToSaveResponse(this.municipalityService.update(municipality));
    } else {
      this.subscribeToSaveResponse(this.municipalityService.create(municipality));
    }
  }

  private createFromForm(): IMunicipality {
    return {
      ...new Municipality(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      governorateId: this.editForm.get(['governorateId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMunicipality>>): void {
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

  trackById(index: number, item: IGovernorate): any {
    return item.id;
  }
}
