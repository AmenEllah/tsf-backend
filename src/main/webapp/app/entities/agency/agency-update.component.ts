import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IAgency, Agency } from 'app/shared/model/agency.model';
import { AgencyService } from './agency.service';
import { IMunicipality } from 'app/shared/model/municipality.model';
import { MunicipalityService } from 'app/entities/municipality/municipality.service';

@Component({
  selector: 'jhi-agency-update',
  templateUrl: './agency-update.component.html',
})
export class AgencyUpdateComponent implements OnInit {
  isSaving = false;
  municipalities: IMunicipality[] = [];

  editForm = this.fb.group({
    id: [],
    name: [],
    address: [],
    zip: [],
    city: [],
    municipalityId: [],
  });

  constructor(
    protected agencyService: AgencyService,
    protected municipalityService: MunicipalityService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ agency }) => {
      this.updateForm(agency);

      this.municipalityService.query().subscribe((res: HttpResponse<IMunicipality[]>) => (this.municipalities = res.body || []));
    });
  }

  updateForm(agency: IAgency): void {
    this.editForm.patchValue({
      id: agency.id,
      name: agency.name,
      address: agency.address,
      zip: agency.zip,
      city: agency.city,
      municipalityId: agency.municipalityId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const agency = this.createFromForm();
    if (agency.id !== undefined) {
      this.subscribeToSaveResponse(this.agencyService.update(agency));
    } else {
      this.subscribeToSaveResponse(this.agencyService.create(agency));
    }
  }

  private createFromForm(): IAgency {
    return {
      ...new Agency(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      address: this.editForm.get(['address'])!.value,
      zip: this.editForm.get(['zip'])!.value,
      city: this.editForm.get(['city'])!.value,
      municipalityId: this.editForm.get(['municipalityId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAgency>>): void {
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

  trackById(index: number, item: IMunicipality): any {
    return item.id;
  }
}
