import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IAdressInfo, AdressInfo } from 'app/shared/model/adress-info.model';
import { AdressInfoService } from './adress-info.service';
import { IPersonalInfo } from 'app/shared/model/personal-info.model';
import { PersonalInfoService } from 'app/entities/personal-info/personal-info.service';

@Component({
  selector: 'jhi-adress-info-update',
  templateUrl: './adress-info-update.component.html',
})
export class AdressInfoUpdateComponent implements OnInit {
  isSaving = false;
  personalinfos: IPersonalInfo[] = [];

  editForm = this.fb.group({
    id: [],
    countryOfResidence: [],
    address: [],
    zip: [],
    city: [],
    personalInfoId: [],
  });

  constructor(
    protected adressInfoService: AdressInfoService,
    protected personalInfoService: PersonalInfoService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ adressInfo }) => {
      this.updateForm(adressInfo);

      this.personalInfoService
        .query({ filter: 'adressinfo-is-null' })
        .pipe(
          map((res: HttpResponse<IPersonalInfo[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IPersonalInfo[]) => {
          if (!adressInfo.personalInfoId) {
            this.personalinfos = resBody;
          } else {
            this.personalInfoService
              .find(adressInfo.personalInfoId)
              .pipe(
                map((subRes: HttpResponse<IPersonalInfo>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IPersonalInfo[]) => (this.personalinfos = concatRes));
          }
        });
    });
  }

  updateForm(adressInfo: IAdressInfo): void {
    this.editForm.patchValue({
      id: adressInfo.id,
      countryOfResidence: adressInfo.countryOfResidence,
      address: adressInfo.address,
      zip: adressInfo.zip,
      city: adressInfo.city,
      personalInfoId: adressInfo.personalInfoId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const adressInfo = this.createFromForm();
    if (adressInfo.id !== undefined) {
      this.subscribeToSaveResponse(this.adressInfoService.update(adressInfo));
    } else {
      this.subscribeToSaveResponse(this.adressInfoService.create(adressInfo));
    }
  }

  private createFromForm(): IAdressInfo {
    return {
      ...new AdressInfo(),
      id: this.editForm.get(['id'])!.value,
      countryOfResidence: this.editForm.get(['countryOfResidence'])!.value,
      address: this.editForm.get(['address'])!.value,
      zip: this.editForm.get(['zip'])!.value,
      city: this.editForm.get(['city'])!.value,
      personalInfoId: this.editForm.get(['personalInfoId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAdressInfo>>): void {
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

  trackById(index: number, item: IPersonalInfo): any {
    return item.id;
  }
}
