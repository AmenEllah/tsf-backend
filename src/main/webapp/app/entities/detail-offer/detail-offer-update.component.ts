import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IDetailOffer, DetailOffer } from 'app/shared/model/detail-offer.model';
import { DetailOfferService } from './detail-offer.service';

@Component({
  selector: 'jhi-detail-offer-update',
  templateUrl: './detail-offer-update.component.html',
})
export class DetailOfferUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    description: [],
  });

  constructor(protected detailOfferService: DetailOfferService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ detailOffer }) => {
      this.updateForm(detailOffer);
    });
  }

  updateForm(detailOffer: IDetailOffer): void {
    this.editForm.patchValue({
      id: detailOffer.id,
      description: detailOffer.description,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const detailOffer = this.createFromForm();
    if (detailOffer.id !== undefined) {
      this.subscribeToSaveResponse(this.detailOfferService.update(detailOffer));
    } else {
      this.subscribeToSaveResponse(this.detailOfferService.create(detailOffer));
    }
  }

  private createFromForm(): IDetailOffer {
    return {
      ...new DetailOffer(),
      id: this.editForm.get(['id'])!.value,
      description: this.editForm.get(['description'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDetailOffer>>): void {
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
