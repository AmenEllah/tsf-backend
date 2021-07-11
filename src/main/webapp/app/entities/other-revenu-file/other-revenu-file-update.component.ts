import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IOtherRevenuFile, OtherRevenuFile } from 'app/shared/model/other-revenu-file.model';
import { OtherRevenuFileService } from './other-revenu-file.service';
import { IRequiredDoc } from 'app/shared/model/required-doc.model';
import { RequiredDocService } from 'app/entities/required-doc/required-doc.service';
import { IJustifRevenu } from 'app/shared/model/justif-revenu.model';
import { JustifRevenuService } from 'app/entities/justif-revenu/justif-revenu.service';

type SelectableEntity = IRequiredDoc | IJustifRevenu;

@Component({
  selector: 'jhi-other-revenu-file-update',
  templateUrl: './other-revenu-file-update.component.html',
})
export class OtherRevenuFileUpdateComponent implements OnInit {
  isSaving = false;
  requireddocs: IRequiredDoc[] = [];
  justifrevenus: IJustifRevenu[] = [];

  editForm = this.fb.group({
    id: [],
    path: [],
    requiredDocId: [],
    justifRevenuId: [],
  });

  constructor(
    protected otherRevenuFileService: OtherRevenuFileService,
    protected requiredDocService: RequiredDocService,
    protected justifRevenuService: JustifRevenuService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ otherRevenuFile }) => {
      this.updateForm(otherRevenuFile);

      this.requiredDocService.query().subscribe((res: HttpResponse<IRequiredDoc[]>) => (this.requireddocs = res.body || []));

      this.justifRevenuService.query().subscribe((res: HttpResponse<IJustifRevenu[]>) => (this.justifrevenus = res.body || []));
    });
  }

  updateForm(otherRevenuFile: IOtherRevenuFile): void {
    this.editForm.patchValue({
      id: otherRevenuFile.id,
      path: otherRevenuFile.path,
      requiredDocId: otherRevenuFile.requiredDocId,
      justifRevenuId: otherRevenuFile.justifRevenuId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const otherRevenuFile = this.createFromForm();
    if (otherRevenuFile.id !== undefined) {
      this.subscribeToSaveResponse(this.otherRevenuFileService.update(otherRevenuFile));
    } else {
      this.subscribeToSaveResponse(this.otherRevenuFileService.create(otherRevenuFile));
    }
  }

  private createFromForm(): IOtherRevenuFile {
    return {
      ...new OtherRevenuFile(),
      id: this.editForm.get(['id'])!.value,
      path: this.editForm.get(['path'])!.value,
      requiredDocId: this.editForm.get(['requiredDocId'])!.value,
      justifRevenuId: this.editForm.get(['justifRevenuId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOtherRevenuFile>>): void {
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
