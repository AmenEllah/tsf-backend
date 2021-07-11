import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IRequest, Request } from 'app/shared/model/request.model';
import { RequestService } from './request.service';
import { IOffer } from 'app/shared/model/offer.model';
import { OfferService } from 'app/entities/offer/offer.service';

@Component({
  selector: 'jhi-request-update',
  templateUrl: './request-update.component.html',
})
export class RequestUpdateComponent implements OnInit {
  isSaving = false;
  offers: IOffer[] = [];
  visioDateDp: any;
  sendingMailDateDp: any;

  editForm = this.fb.group({
    id: [],
    visioDate: [],
    sendingMailDate: [],
    state: [],
    step: [],
    codeVerification: [],
    amplitudeRef: [],
    requestState: [],
    requestStatus: [],
    offerId: [],
  });

  constructor(
    protected requestService: RequestService,
    protected offerService: OfferService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ request }) => {
      this.updateForm(request);

      this.offerService.query().subscribe((res: HttpResponse<IOffer[]>) => (this.offers = res.body || []));
    });
  }

  updateForm(request: IRequest): void {
    this.editForm.patchValue({
      id: request.id,
      visioDate: request.visioDate,
      sendingMailDate: request.sendingMailDate,
      state: request.state,
      step: request.step,
      codeVerification: request.codeVerification,
      amplitudeRef: request.amplitudeRef,
      requestState: request.requestState,
      requestStatus: request.requestStatus,
      offerId: request.offerId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const request = this.createFromForm();
    if (request.id !== undefined) {
      this.subscribeToSaveResponse(this.requestService.update(request));
    } else {
      this.subscribeToSaveResponse(this.requestService.create(request));
    }
  }

  private createFromForm(): IRequest {
    return {
      ...new Request(),
      id: this.editForm.get(['id'])!.value,
      visioDate: this.editForm.get(['visioDate'])!.value,
      sendingMailDate: this.editForm.get(['sendingMailDate'])!.value,
      state: this.editForm.get(['state'])!.value,
      step: this.editForm.get(['step'])!.value,
      codeVerification: this.editForm.get(['codeVerification'])!.value,
      amplitudeRef: this.editForm.get(['amplitudeRef'])!.value,
      requestState: this.editForm.get(['requestState'])!.value,
      requestStatus: this.editForm.get(['requestStatus'])!.value,
      offerId: this.editForm.get(['offerId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRequest>>): void {
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

  trackById(index: number, item: IOffer): any {
    return item.id;
  }
}
