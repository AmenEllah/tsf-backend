import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ISupplyMatrix, SupplyMatrix } from 'app/shared/model/supply-matrix.model';
import { SupplyMatrixService } from './supply-matrix.service';

@Component({
  selector: 'jhi-supply-matrix-update',
  templateUrl: './supply-matrix-update.component.html',
})
export class SupplyMatrixUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    categoryId: [],
    incomeTypeCode: [],
    incomeType: [],
    monthlyIncomeId: [],
    marketCode: [],
    market: [],
    segmentCode: [],
    segment: [],
    activityId: [],
    professionCode: [],
    profession: [],
  });

  constructor(protected supplyMatrixService: SupplyMatrixService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ supplyMatrix }) => {
      this.updateForm(supplyMatrix);
    });
  }

  updateForm(supplyMatrix: ISupplyMatrix): void {
    this.editForm.patchValue({
      id: supplyMatrix.id,
      categoryId: supplyMatrix.categoryId,
      incomeTypeCode: supplyMatrix.incomeTypeCode,
      incomeType: supplyMatrix.incomeType,
      monthlyIncomeId: supplyMatrix.monthlyIncomeId,
      marketCode: supplyMatrix.marketCode,
      market: supplyMatrix.market,
      segmentCode: supplyMatrix.segmentCode,
      segment: supplyMatrix.segment,
      activityId: supplyMatrix.activityId,
      professionCode: supplyMatrix.professionCode,
      profession: supplyMatrix.profession,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const supplyMatrix = this.createFromForm();
    if (supplyMatrix.id !== undefined) {
      this.subscribeToSaveResponse(this.supplyMatrixService.update(supplyMatrix));
    } else {
      this.subscribeToSaveResponse(this.supplyMatrixService.create(supplyMatrix));
    }
  }

  private createFromForm(): ISupplyMatrix {
    return {
      ...new SupplyMatrix(),
      id: this.editForm.get(['id'])!.value,
      categoryId: this.editForm.get(['categoryId'])!.value,
      incomeTypeCode: this.editForm.get(['incomeTypeCode'])!.value,
      incomeType: this.editForm.get(['incomeType'])!.value,
      monthlyIncomeId: this.editForm.get(['monthlyIncomeId'])!.value,
      marketCode: this.editForm.get(['marketCode'])!.value,
      market: this.editForm.get(['market'])!.value,
      segmentCode: this.editForm.get(['segmentCode'])!.value,
      segment: this.editForm.get(['segment'])!.value,
      activityId: this.editForm.get(['activityId'])!.value,
      professionCode: this.editForm.get(['professionCode'])!.value,
      profession: this.editForm.get(['profession'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISupplyMatrix>>): void {
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
