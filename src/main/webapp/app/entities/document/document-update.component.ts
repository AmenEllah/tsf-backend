import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IDocument, Document } from 'app/shared/model/document.model';
import { DocumentService } from './document.service';
import { IRequest } from 'app/shared/model/request.model';
import { RequestService } from 'app/entities/request/request.service';

@Component({
  selector: 'jhi-document-update',
  templateUrl: './document-update.component.html',
})
export class DocumentUpdateComponent implements OnInit {
  isSaving = false;
  requests: IRequest[] = [];
  dateCreationDp: any;
  dateUpdateDp: any;

  editForm = this.fb.group({
    id: [],
    typeDocument: [],
    nomFichier: [],
    emplacement: [],
    idDossierSigned: [],
    hasSigned: [],
    dateCreation: [],
    dateUpdate: [],
    requestId: [],
  });

  constructor(
    protected documentService: DocumentService,
    protected requestService: RequestService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ document }) => {
      this.updateForm(document);

      this.requestService
        .query({ filter: 'document-is-null' })
        .pipe(
          map((res: HttpResponse<IRequest[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IRequest[]) => {
          if (!document.requestId) {
            this.requests = resBody;
          } else {
            this.requestService
              .find(document.requestId)
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

  updateForm(document: IDocument): void {
    this.editForm.patchValue({
      id: document.id,
      typeDocument: document.typeDocument,
      nomFichier: document.nomFichier,
      emplacement: document.emplacement,
      idDossierSigned: document.idDossierSigned,
      hasSigned: document.hasSigned,
      dateCreation: document.dateCreation,
      dateUpdate: document.dateUpdate,
      requestId: document.requestId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const document = this.createFromForm();
    if (document.id !== undefined) {
      this.subscribeToSaveResponse(this.documentService.update(document));
    } else {
      this.subscribeToSaveResponse(this.documentService.create(document));
    }
  }

  private createFromForm(): IDocument {
    return {
      ...new Document(),
      id: this.editForm.get(['id'])!.value,
      typeDocument: this.editForm.get(['typeDocument'])!.value,
      nomFichier: this.editForm.get(['nomFichier'])!.value,
      emplacement: this.editForm.get(['emplacement'])!.value,
      idDossierSigned: this.editForm.get(['idDossierSigned'])!.value,
      hasSigned: this.editForm.get(['hasSigned'])!.value,
      dateCreation: this.editForm.get(['dateCreation'])!.value,
      dateUpdate: this.editForm.get(['dateUpdate'])!.value,
      requestId: this.editForm.get(['requestId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDocument>>): void {
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
