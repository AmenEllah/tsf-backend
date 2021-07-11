import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IRequiredDocResidency, RequiredDocResidency } from 'app/shared/model/required-doc-residency.model';
import { RequiredDocResidencyService } from './required-doc-residency.service';
import { IRequiredDoc } from 'app/shared/model/required-doc.model';
import { RequiredDocService } from 'app/entities/required-doc/required-doc.service';
import { IResidencyDocument } from 'app/shared/model/residency-document.model';
import { ResidencyDocumentService } from 'app/entities/residency-document/residency-document.service';

type SelectableEntity = IRequiredDoc | IResidencyDocument;

@Component({
  selector: 'jhi-required-doc-residency-update',
  templateUrl: './required-doc-residency-update.component.html',
})
export class RequiredDocResidencyUpdateComponent implements OnInit {
  isSaving = false;
  requireddocs: IRequiredDoc[] = [];
  residencydocuments: IResidencyDocument[] = [];
  deliveryDateDp: any;
  experationDateDp: any;

  editForm = this.fb.group({
    id: [],
    type: [],
    num: [],
    deliveryDate: [],
    experationDate: [],
    illimitedExperationDate: [],
    residencyRecto: [],
    residencyVerso: [],
    requiredDocId: [],
    residencyDocumentId: [],
  });

  constructor(
    protected requiredDocResidencyService: RequiredDocResidencyService,
    protected requiredDocService: RequiredDocService,
    protected residencyDocumentService: ResidencyDocumentService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ requiredDocResidency }) => {
      this.updateForm(requiredDocResidency);

      this.requiredDocService.query().subscribe((res: HttpResponse<IRequiredDoc[]>) => (this.requireddocs = res.body || []));

      this.residencyDocumentService
        .query({ filter: 'requireddocresidency-is-null' })
        .pipe(
          map((res: HttpResponse<IResidencyDocument[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IResidencyDocument[]) => {
          if (!requiredDocResidency.residencyDocumentId) {
            this.residencydocuments = resBody;
          } else {
            this.residencyDocumentService
              .find(requiredDocResidency.residencyDocumentId)
              .pipe(
                map((subRes: HttpResponse<IResidencyDocument>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IResidencyDocument[]) => (this.residencydocuments = concatRes));
          }
        });
    });
  }

  updateForm(requiredDocResidency: IRequiredDocResidency): void {
    this.editForm.patchValue({
      id: requiredDocResidency.id,
      type: requiredDocResidency.type,
      num: requiredDocResidency.num,
      deliveryDate: requiredDocResidency.deliveryDate,
      experationDate: requiredDocResidency.experationDate,
      illimitedExperationDate: requiredDocResidency.illimitedExperationDate,
      residencyRecto: requiredDocResidency.residencyRecto,
      residencyVerso: requiredDocResidency.residencyVerso,
      requiredDocId: requiredDocResidency.requiredDocId,
      residencyDocumentId: requiredDocResidency.residencyDocumentId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const requiredDocResidency = this.createFromForm();
    if (requiredDocResidency.id !== undefined) {
      this.subscribeToSaveResponse(this.requiredDocResidencyService.update(requiredDocResidency));
    } else {
      this.subscribeToSaveResponse(this.requiredDocResidencyService.create(requiredDocResidency));
    }
  }

  private createFromForm(): IRequiredDocResidency {
    return {
      ...new RequiredDocResidency(),
      id: this.editForm.get(['id'])!.value,
      type: this.editForm.get(['type'])!.value,
      num: this.editForm.get(['num'])!.value,
      deliveryDate: this.editForm.get(['deliveryDate'])!.value,
      experationDate: this.editForm.get(['experationDate'])!.value,
      illimitedExperationDate: this.editForm.get(['illimitedExperationDate'])!.value,
      residencyRecto: this.editForm.get(['residencyRecto'])!.value,
      residencyVerso: this.editForm.get(['residencyVerso'])!.value,
      requiredDocId: this.editForm.get(['requiredDocId'])!.value,
      residencyDocumentId: this.editForm.get(['residencyDocumentId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRequiredDocResidency>>): void {
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
