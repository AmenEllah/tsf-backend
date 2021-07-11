import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IOtherResidencyFile, OtherResidencyFile } from 'app/shared/model/other-residency-file.model';
import { OtherResidencyFileService } from './other-residency-file.service';
import { IRequiredDoc } from 'app/shared/model/required-doc.model';
import { RequiredDocService } from 'app/entities/required-doc/required-doc.service';
import { IResidencyDocument } from 'app/shared/model/residency-document.model';
import { ResidencyDocumentService } from 'app/entities/residency-document/residency-document.service';

type SelectableEntity = IRequiredDoc | IResidencyDocument;

@Component({
  selector: 'jhi-other-residency-file-update',
  templateUrl: './other-residency-file-update.component.html',
})
export class OtherResidencyFileUpdateComponent implements OnInit {
  isSaving = false;
  requireddocs: IRequiredDoc[] = [];
  residencydocuments: IResidencyDocument[] = [];

  editForm = this.fb.group({
    id: [],
    path: [],
    requiredDocId: [],
    residencyDocumentId: [],
  });

  constructor(
    protected otherResidencyFileService: OtherResidencyFileService,
    protected requiredDocService: RequiredDocService,
    protected residencyDocumentService: ResidencyDocumentService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ otherResidencyFile }) => {
      this.updateForm(otherResidencyFile);

      this.requiredDocService.query().subscribe((res: HttpResponse<IRequiredDoc[]>) => (this.requireddocs = res.body || []));

      this.residencyDocumentService
        .query()
        .subscribe((res: HttpResponse<IResidencyDocument[]>) => (this.residencydocuments = res.body || []));
    });
  }

  updateForm(otherResidencyFile: IOtherResidencyFile): void {
    this.editForm.patchValue({
      id: otherResidencyFile.id,
      path: otherResidencyFile.path,
      requiredDocId: otherResidencyFile.requiredDocId,
      residencyDocumentId: otherResidencyFile.residencyDocumentId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const otherResidencyFile = this.createFromForm();
    if (otherResidencyFile.id !== undefined) {
      this.subscribeToSaveResponse(this.otherResidencyFileService.update(otherResidencyFile));
    } else {
      this.subscribeToSaveResponse(this.otherResidencyFileService.create(otherResidencyFile));
    }
  }

  private createFromForm(): IOtherResidencyFile {
    return {
      ...new OtherResidencyFile(),
      id: this.editForm.get(['id'])!.value,
      path: this.editForm.get(['path'])!.value,
      requiredDocId: this.editForm.get(['requiredDocId'])!.value,
      residencyDocumentId: this.editForm.get(['residencyDocumentId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOtherResidencyFile>>): void {
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
