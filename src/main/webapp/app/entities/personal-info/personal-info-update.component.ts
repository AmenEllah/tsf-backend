import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IPersonalInfo, PersonalInfo } from 'app/shared/model/personal-info.model';
import { PersonalInfoService } from './personal-info.service';
import { IRequest } from 'app/shared/model/request.model';
import { RequestService } from 'app/entities/request/request.service';
import { IAgency } from 'app/shared/model/agency.model';
import { AgencyService } from 'app/entities/agency/agency.service';

type SelectableEntity = IRequest | IAgency;

@Component({
  selector: 'jhi-personal-info-update',
  templateUrl: './personal-info-update.component.html',
})
export class PersonalInfoUpdateComponent implements OnInit {
  isSaving = false;
  requests: IRequest[] = [];
  agencies: IAgency[] = [];
  birthdayDp: any;

  editForm = this.fb.group({
    id: [],
    civility: [],
    firstName: [],
    lastName: [],
    email: [],
    nativeCountry: [],
    birthday: [],
    clientABT: [],
    rib: [],
    nationality: [],
    secondNationality: [],
    nbrkids: [],
    maritalStatus: [],
    phone: [],
    americanIndex: [],
    accountNumber: [],
    cin: [],
    abroadResidancyProof: [],
    abroadResidancyNumber: [],
    cinDeliveryDate: [],
    abroadResidancyDeliveryDate: [],
    abroadResidancyExpirationDate: [],
    requestId: [],
    agencyId: [],
  });

  constructor(
    protected personalInfoService: PersonalInfoService,
    protected requestService: RequestService,
    protected agencyService: AgencyService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ personalInfo }) => {
      if (!personalInfo.id) {
        const today = moment().startOf('day');
        personalInfo.cinDeliveryDate = today;
        personalInfo.abroadResidancyDeliveryDate = today;
        personalInfo.abroadResidancyExpirationDate = today;
      }

      this.updateForm(personalInfo);

      this.requestService
        .query({ filter: 'personalinfo-is-null' })
        .pipe(
          map((res: HttpResponse<IRequest[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IRequest[]) => {
          if (!personalInfo.requestId) {
            this.requests = resBody;
          } else {
            this.requestService
              .find(personalInfo.requestId)
              .pipe(
                map((subRes: HttpResponse<IRequest>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IRequest[]) => (this.requests = concatRes));
          }
        });

      this.agencyService.query().subscribe((res: HttpResponse<IAgency[]>) => (this.agencies = res.body || []));
    });
  }

  updateForm(personalInfo: IPersonalInfo): void {
    this.editForm.patchValue({
      id: personalInfo.id,
      civility: personalInfo.civility,
      firstName: personalInfo.firstName,
      lastName: personalInfo.lastName,
      email: personalInfo.email,
      nativeCountry: personalInfo.nativeCountry,
      birthday: personalInfo.birthday,
      clientABT: personalInfo.clientABT,
      rib: personalInfo.rib,
      nationality: personalInfo.nationality,
      secondNationality: personalInfo.secondNationality,
      nbrkids: personalInfo.nbrkids,
      maritalStatus: personalInfo.maritalStatus,
      phone: personalInfo.phone,
      americanIndex: personalInfo.americanIndex,
      accountNumber: personalInfo.accountNumber,
      cin: personalInfo.cin,
      abroadResidancyProof: personalInfo.abroadResidancyProof,
      abroadResidancyNumber: personalInfo.abroadResidancyNumber,
      cinDeliveryDate: personalInfo.cinDeliveryDate ? personalInfo.cinDeliveryDate.format(DATE_TIME_FORMAT) : null,
      abroadResidancyDeliveryDate: personalInfo.abroadResidancyDeliveryDate
        ? personalInfo.abroadResidancyDeliveryDate.format(DATE_TIME_FORMAT)
        : null,
      abroadResidancyExpirationDate: personalInfo.abroadResidancyExpirationDate
        ? personalInfo.abroadResidancyExpirationDate.format(DATE_TIME_FORMAT)
        : null,
      requestId: personalInfo.requestId,
      agencyId: personalInfo.agencyId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const personalInfo = this.createFromForm();
    if (personalInfo.id !== undefined) {
      this.subscribeToSaveResponse(this.personalInfoService.update(personalInfo));
    } else {
      this.subscribeToSaveResponse(this.personalInfoService.create(personalInfo));
    }
  }

  private createFromForm(): IPersonalInfo {
    return {
      ...new PersonalInfo(),
      id: this.editForm.get(['id'])!.value,
      civility: this.editForm.get(['civility'])!.value,
      firstName: this.editForm.get(['firstName'])!.value,
      lastName: this.editForm.get(['lastName'])!.value,
      email: this.editForm.get(['email'])!.value,
      nativeCountry: this.editForm.get(['nativeCountry'])!.value,
      birthday: this.editForm.get(['birthday'])!.value,
      clientABT: this.editForm.get(['clientABT'])!.value,
      rib: this.editForm.get(['rib'])!.value,
      nationality: this.editForm.get(['nationality'])!.value,
      secondNationality: this.editForm.get(['secondNationality'])!.value,
      nbrkids: this.editForm.get(['nbrkids'])!.value,
      maritalStatus: this.editForm.get(['maritalStatus'])!.value,
      phone: this.editForm.get(['phone'])!.value,
      americanIndex: this.editForm.get(['americanIndex'])!.value,
      accountNumber: this.editForm.get(['accountNumber'])!.value,
      cin: this.editForm.get(['cin'])!.value,
      abroadResidancyProof: this.editForm.get(['abroadResidancyProof'])!.value,
      abroadResidancyNumber: this.editForm.get(['abroadResidancyNumber'])!.value,
      cinDeliveryDate: this.editForm.get(['cinDeliveryDate'])!.value
        ? moment(this.editForm.get(['cinDeliveryDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      abroadResidancyDeliveryDate: this.editForm.get(['abroadResidancyDeliveryDate'])!.value
        ? moment(this.editForm.get(['abroadResidancyDeliveryDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      abroadResidancyExpirationDate: this.editForm.get(['abroadResidancyExpirationDate'])!.value
        ? moment(this.editForm.get(['abroadResidancyExpirationDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      requestId: this.editForm.get(['requestId'])!.value,
      agencyId: this.editForm.get(['agencyId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPersonalInfo>>): void {
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
