import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IReqDocUpload, ReqDocUpload } from 'app/shared/model/req-doc-upload.model';
import { ReqDocUploadService } from './req-doc-upload.service';
import { IRequest } from 'app/shared/model/request.model';
import { RequestService } from 'app/entities/request/request.service';

@Component({
  selector: 'jhi-req-doc-upload-update',
  templateUrl: './req-doc-upload-update.component.html',
})
export class ReqDocUploadUpdateComponent implements OnInit {
  isSaving = false;
  requests: IRequest[] = [];
  uploadedAtDp: any;

  editForm = this.fb.group({
    id: [],
    pathDoc: [],
    typeDoc: [],
    uploadedAt: [],
    updatedAt: [],
    docStatus: [],
    request: [],
  });

  constructor(
    protected reqDocUploadService: ReqDocUploadService,
    protected requestService: RequestService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ reqDocUpload }) => {
      if (!reqDocUpload.id) {
        const today = moment().startOf('day');
        reqDocUpload.updatedAt = today;
      }

      this.updateForm(reqDocUpload);

      this.requestService.query().subscribe((res: HttpResponse<IRequest[]>) => (this.requests = res.body || []));
    });
  }

  updateForm(reqDocUpload: IReqDocUpload): void {
    this.editForm.patchValue({
      id: reqDocUpload.id,
      pathDoc: reqDocUpload.pathDoc,
      typeDoc: reqDocUpload.typeDoc,
      uploadedAt: reqDocUpload.uploadedAt,
      updatedAt: reqDocUpload.updatedAt ? reqDocUpload.updatedAt.format(DATE_TIME_FORMAT) : null,
      docStatus: reqDocUpload.docStatus,
      request: reqDocUpload.request,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const reqDocUpload = this.createFromForm();
    if (reqDocUpload.id !== undefined) {
      this.subscribeToSaveResponse(this.reqDocUploadService.update(reqDocUpload));
    } else {
      this.subscribeToSaveResponse(this.reqDocUploadService.create(reqDocUpload));
    }
  }

  private createFromForm(): IReqDocUpload {
    return {
      ...new ReqDocUpload(),
      id: this.editForm.get(['id'])!.value,
      pathDoc: this.editForm.get(['pathDoc'])!.value,
      typeDoc: this.editForm.get(['typeDoc'])!.value,
      uploadedAt: this.editForm.get(['uploadedAt'])!.value,
      updatedAt: this.editForm.get(['updatedAt'])!.value ? moment(this.editForm.get(['updatedAt'])!.value, DATE_TIME_FORMAT) : undefined,
      docStatus: this.editForm.get(['docStatus'])!.value,
      request: this.editForm.get(['request'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IReqDocUpload>>): void {
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
