import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ISubscriberStatus, SubscriberStatus } from 'app/shared/model/subscriber-status.model';
import { SubscriberStatusService } from './subscriber-status.service';

@Component({
  selector: 'jhi-subscriber-status-update',
  templateUrl: './subscriber-status-update.component.html',
})
export class SubscriberStatusUpdateComponent implements OnInit {
  isSaving = false;
  dateAppelVisioDp: any;

  editForm = this.fb.group({
    id: [],
    email: [],
    numCin: [],
    withAppelVisio: [],
    withCertif: [],
    withSignature: [],
    dateAppelVisio: [],
  });

  constructor(
    protected subscriberStatusService: SubscriberStatusService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ subscriberStatus }) => {
      this.updateForm(subscriberStatus);
    });
  }

  updateForm(subscriberStatus: ISubscriberStatus): void {
    this.editForm.patchValue({
      id: subscriberStatus.id,
      email: subscriberStatus.email,
      numCin: subscriberStatus.numCin,
      withAppelVisio: subscriberStatus.withAppelVisio,
      withCertif: subscriberStatus.withCertif,
      withSignature: subscriberStatus.withSignature,
      dateAppelVisio: subscriberStatus.dateAppelVisio,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const subscriberStatus = this.createFromForm();
    if (subscriberStatus.id !== undefined) {
      this.subscribeToSaveResponse(this.subscriberStatusService.update(subscriberStatus));
    } else {
      this.subscribeToSaveResponse(this.subscriberStatusService.create(subscriberStatus));
    }
  }

  private createFromForm(): ISubscriberStatus {
    return {
      ...new SubscriberStatus(),
      id: this.editForm.get(['id'])!.value,
      email: this.editForm.get(['email'])!.value,
      numCin: this.editForm.get(['numCin'])!.value,
      withAppelVisio: this.editForm.get(['withAppelVisio'])!.value,
      withCertif: this.editForm.get(['withCertif'])!.value,
      withSignature: this.editForm.get(['withSignature'])!.value,
      dateAppelVisio: this.editForm.get(['dateAppelVisio'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISubscriberStatus>>): void {
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
