import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IRequiredDoc, RequiredDoc } from 'app/shared/model/required-doc.model';
import { RequiredDocService } from './required-doc.service';
import { IRequest } from 'app/shared/model/request.model';
import { RequestService } from 'app/entities/request/request.service';

@Component({
  selector: 'jhi-required-doc-update',
  templateUrl: './required-doc-update.component.html',
})
export class RequiredDocUpdateComponent implements OnInit {
  isSaving = false;
  requests: IRequest[] = [];
  deliveryDateCinDp: any;

  editForm = this.fb.group({
    id: [],
    label: [],
    type: [],
    numCIN: [],
    deliveryDateCin: [],
    rectoCin: [],
    versoCin: [],
    fatca: [],
    requestId: [],
  });

  constructor(
    protected requiredDocService: RequiredDocService,
    protected requestService: RequestService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ requiredDoc }) => {
      this.updateForm(requiredDoc);

      this.requestService
        .query({ filter: 'requireddoc-is-null' })
        .pipe(
          map((res: HttpResponse<IRequest[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IRequest[]) => {
          if (!requiredDoc.requestId) {
            this.requests = resBody;
          } else {
            this.requestService
              .find(requiredDoc.requestId)
              .pipe(
                map((subRes: HttpResponse<IRequest>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IRequest[]) => (this.requests = concatRes));
          }
        });
    });
  }

  updateForm(requiredDoc: IRequiredDoc): void {
    this.editForm.patchValue({
      id: requiredDoc.id,
      label: requiredDoc.label,
      type: requiredDoc.type,
      numCIN: requiredDoc.numCIN,
      deliveryDateCin: requiredDoc.deliveryDateCin,
      rectoCin: requiredDoc.rectoCin,
      versoCin: requiredDoc.versoCin,
      fatca: requiredDoc.fatca,
      requestId: requiredDoc.requestId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const requiredDoc = this.createFromForm();
    if (requiredDoc.id !== undefined) {
      this.subscribeToSaveResponse(this.requiredDocService.update(requiredDoc));
    } else {
      this.subscribeToSaveResponse(this.requiredDocService.create(requiredDoc));
    }
  }

  private createFromForm(): IRequiredDoc {
    return {
      ...new RequiredDoc(),
      id: this.editForm.get(['id'])!.value,
      label: this.editForm.get(['label'])!.value,
      type: this.editForm.get(['type'])!.value,
      numCIN: this.editForm.get(['numCIN'])!.value,
      deliveryDateCin: this.editForm.get(['deliveryDateCin'])!.value,
      rectoCin: this.editForm.get(['rectoCin'])!.value,
      versoCin: this.editForm.get(['versoCin'])!.value,
      fatca: this.editForm.get(['fatca'])!.value,
      requestId: this.editForm.get(['requestId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRequiredDoc>>): void {
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

  trackById(index: number, item: IRequest): any {
    return item.id;
  }
}
