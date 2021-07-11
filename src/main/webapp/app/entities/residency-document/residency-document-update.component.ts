import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IResidencyDocument, ResidencyDocument } from 'app/shared/model/residency-document.model';
import { ResidencyDocumentService } from './residency-document.service';

@Component({
  selector: 'jhi-residency-document-update',
  templateUrl: './residency-document-update.component.html',
})
export class ResidencyDocumentUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    labelFR: [],
    labelEN: [],
  });

  constructor(
    protected residencyDocumentService: ResidencyDocumentService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ residencyDocument }) => {
      this.updateForm(residencyDocument);
    });
  }

  updateForm(residencyDocument: IResidencyDocument): void {
    this.editForm.patchValue({
      id: residencyDocument.id,
      labelFR: residencyDocument.labelFR,
      labelEN: residencyDocument.labelEN,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const residencyDocument = this.createFromForm();
    if (residencyDocument.id !== undefined) {
      this.subscribeToSaveResponse(this.residencyDocumentService.update(residencyDocument));
    } else {
      this.subscribeToSaveResponse(this.residencyDocumentService.create(residencyDocument));
    }
  }

  private createFromForm(): IResidencyDocument {
    return {
      ...new ResidencyDocument(),
      id: this.editForm.get(['id'])!.value,
      labelFR: this.editForm.get(['labelFR'])!.value,
      labelEN: this.editForm.get(['labelEN'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IResidencyDocument>>): void {
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
