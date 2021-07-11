import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IReqDocUpload } from 'app/shared/model/req-doc-upload.model';

@Component({
  selector: 'jhi-req-doc-upload-detail',
  templateUrl: './req-doc-upload-detail.component.html',
})
export class ReqDocUploadDetailComponent implements OnInit {
  reqDocUpload: IReqDocUpload | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ reqDocUpload }) => (this.reqDocUpload = reqDocUpload));
  }

  previousState(): void {
    window.history.back();
  }
}
