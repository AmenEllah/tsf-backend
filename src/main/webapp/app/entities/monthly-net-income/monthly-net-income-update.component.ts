import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IMonthlyNetIncome, MonthlyNetIncome } from 'app/shared/model/monthly-net-income.model';
import { MonthlyNetIncomeService } from './monthly-net-income.service';

@Component({
  selector: 'jhi-monthly-net-income-update',
  templateUrl: './monthly-net-income-update.component.html',
})
export class MonthlyNetIncomeUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    incomesFR: [],
    incomesEN: [],
  });

  constructor(
    protected monthlyNetIncomeService: MonthlyNetIncomeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ monthlyNetIncome }) => {
      this.updateForm(monthlyNetIncome);
    });
  }

  updateForm(monthlyNetIncome: IMonthlyNetIncome): void {
    this.editForm.patchValue({
      id: monthlyNetIncome.id,
      incomesFR: monthlyNetIncome.incomesFR,
      incomesEN: monthlyNetIncome.incomesEN,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const monthlyNetIncome = this.createFromForm();
    if (monthlyNetIncome.id !== undefined) {
      this.subscribeToSaveResponse(this.monthlyNetIncomeService.update(monthlyNetIncome));
    } else {
      this.subscribeToSaveResponse(this.monthlyNetIncomeService.create(monthlyNetIncome));
    }
  }

  private createFromForm(): IMonthlyNetIncome {
    return {
      ...new MonthlyNetIncome(),
      id: this.editForm.get(['id'])!.value,
      incomesFR: this.editForm.get(['incomesFR'])!.value,
      incomesEN: this.editForm.get(['incomesEN'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMonthlyNetIncome>>): void {
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
