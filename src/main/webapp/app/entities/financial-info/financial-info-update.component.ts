import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IFinancialInfo, FinancialInfo } from 'app/shared/model/financial-info.model';
import { FinancialInfoService } from './financial-info.service';
import { IActivity } from 'app/shared/model/activity.model';
import { ActivityService } from 'app/entities/activity/activity.service';
import { ICategory } from 'app/shared/model/category.model';
import { CategoryService } from 'app/entities/category/category.service';
import { IPersonalInfo } from 'app/shared/model/personal-info.model';
import { PersonalInfoService } from 'app/entities/personal-info/personal-info.service';
import { IMonthlyNetIncome } from 'app/shared/model/monthly-net-income.model';
import { MonthlyNetIncomeService } from 'app/entities/monthly-net-income/monthly-net-income.service';

type SelectableEntity = IActivity | ICategory | IPersonalInfo | IMonthlyNetIncome;

@Component({
  selector: 'jhi-financial-info-update',
  templateUrl: './financial-info-update.component.html',
})
export class FinancialInfoUpdateComponent implements OnInit {
  isSaving = false;
  activities: IActivity[] = [];
  categories: ICategory[] = [];
  personalinfos: IPersonalInfo[] = [];
  monthlynetincomes: IMonthlyNetIncome[] = [];

  editForm = this.fb.group({
    id: [],
    activityId: [],
    categoryId: [],
    personalInfoId: [],
    monthlyNetIncomeId: [],
  });

  constructor(
    protected financialInfoService: FinancialInfoService,
    protected activityService: ActivityService,
    protected categoryService: CategoryService,
    protected personalInfoService: PersonalInfoService,
    protected monthlyNetIncomeService: MonthlyNetIncomeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ financialInfo }) => {
      this.updateForm(financialInfo);

      this.activityService.query().subscribe((res: HttpResponse<IActivity[]>) => (this.activities = res.body || []));

      this.categoryService.query().subscribe((res: HttpResponse<ICategory[]>) => (this.categories = res.body || []));

      this.personalInfoService
        .query({ 'financialInfoId.specified': 'false' })
        .pipe(
          map((res: HttpResponse<IPersonalInfo[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IPersonalInfo[]) => {
          if (!financialInfo.personalInfoId) {
            this.personalinfos = resBody;
          } else {
            this.personalInfoService
              .find(financialInfo.personalInfoId)
              .pipe(
                map((subRes: HttpResponse<IPersonalInfo>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IPersonalInfo[]) => (this.personalinfos = concatRes));
          }
        });

      this.monthlyNetIncomeService.query().subscribe((res: HttpResponse<IMonthlyNetIncome[]>) => (this.monthlynetincomes = res.body || []));
    });
  }

  updateForm(financialInfo: IFinancialInfo): void {
    this.editForm.patchValue({
      id: financialInfo.id,
      activityId: financialInfo.activityId,
      categoryId: financialInfo.categoryId,
      personalInfoId: financialInfo.personalInfoId,
      monthlyNetIncomeId: financialInfo.monthlyNetIncomeId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const financialInfo = this.createFromForm();
    if (financialInfo.id !== undefined) {
      this.subscribeToSaveResponse(this.financialInfoService.update(financialInfo));
    } else {
      this.subscribeToSaveResponse(this.financialInfoService.create(financialInfo));
    }
  }

  private createFromForm(): IFinancialInfo {
    return {
      ...new FinancialInfo(),
      id: this.editForm.get(['id'])!.value,
      activityId: this.editForm.get(['activityId'])!.value,
      categoryId: this.editForm.get(['categoryId'])!.value,
      personalInfoId: this.editForm.get(['personalInfoId'])!.value,
      monthlyNetIncomeId: this.editForm.get(['monthlyNetIncomeId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFinancialInfo>>): void {
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
