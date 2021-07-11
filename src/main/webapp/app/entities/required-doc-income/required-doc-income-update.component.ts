import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IRequiredDocIncome, RequiredDocIncome } from 'app/shared/model/required-doc-income.model';
import { RequiredDocIncomeService } from './required-doc-income.service';
import { IRequiredDoc } from 'app/shared/model/required-doc.model';
import { RequiredDocService } from 'app/entities/required-doc/required-doc.service';

@Component({
  selector: 'jhi-required-doc-income-update',
  templateUrl: './required-doc-income-update.component.html',
})
export class RequiredDocIncomeUpdateComponent implements OnInit {
  isSaving = false;
  requireddocs: IRequiredDoc[] = [];

  editForm = this.fb.group({
    id: [],
    type: [],
    path: [],
    requiredDocId: [],
  });

  constructor(
    protected requiredDocIncomeService: RequiredDocIncomeService,
    protected requiredDocService: RequiredDocService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ requiredDocIncome }) => {
      this.updateForm(requiredDocIncome);

      this.requiredDocService.query().subscribe((res: HttpResponse<IRequiredDoc[]>) => (this.requireddocs = res.body || []));
    });
  }

  updateForm(requiredDocIncome: IRequiredDocIncome): void {
    this.editForm.patchValue({
      id: requiredDocIncome.id,
      type: requiredDocIncome.type,
      path: requiredDocIncome.path,
      requiredDocId: requiredDocIncome.requiredDocId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const requiredDocIncome = this.createFromForm();
    if (requiredDocIncome.id !== undefined) {
      this.subscribeToSaveResponse(this.requiredDocIncomeService.update(requiredDocIncome));
    } else {
      this.subscribeToSaveResponse(this.requiredDocIncomeService.create(requiredDocIncome));
    }
  }

  private createFromForm(): IRequiredDocIncome {
    return {
      ...new RequiredDocIncome(),
      id: this.editForm.get(['id'])!.value,
      type: this.editForm.get(['type'])!.value,
      path: this.editForm.get(['path'])!.value,
      requiredDocId: this.editForm.get(['requiredDocId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRequiredDocIncome>>): void {
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

  trackById(index: number, item: IRequiredDoc): any {
    return item.id;
  }
}
