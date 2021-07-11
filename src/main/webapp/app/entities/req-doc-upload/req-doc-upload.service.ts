import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IReqDocUpload } from 'app/shared/model/req-doc-upload.model';

type EntityResponseType = HttpResponse<IReqDocUpload>;
type EntityArrayResponseType = HttpResponse<IReqDocUpload[]>;

@Injectable({ providedIn: 'root' })
export class ReqDocUploadService {
  public resourceUrl = SERVER_API_URL + 'api/req-doc-uploads';

  constructor(protected http: HttpClient) {}

  create(reqDocUpload: IReqDocUpload): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(reqDocUpload);
    return this.http
      .post<IReqDocUpload>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(reqDocUpload: IReqDocUpload): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(reqDocUpload);
    return this.http
      .put<IReqDocUpload>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IReqDocUpload>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IReqDocUpload[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(reqDocUpload: IReqDocUpload): IReqDocUpload {
    const copy: IReqDocUpload = Object.assign({}, reqDocUpload, {
      uploadedAt: reqDocUpload.uploadedAt && reqDocUpload.uploadedAt.isValid() ? reqDocUpload.uploadedAt.format(DATE_FORMAT) : undefined,
      updatedAt: reqDocUpload.updatedAt && reqDocUpload.updatedAt.isValid() ? reqDocUpload.updatedAt.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.uploadedAt = res.body.uploadedAt ? moment(res.body.uploadedAt) : undefined;
      res.body.updatedAt = res.body.updatedAt ? moment(res.body.updatedAt) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((reqDocUpload: IReqDocUpload) => {
        reqDocUpload.uploadedAt = reqDocUpload.uploadedAt ? moment(reqDocUpload.uploadedAt) : undefined;
        reqDocUpload.updatedAt = reqDocUpload.updatedAt ? moment(reqDocUpload.updatedAt) : undefined;
      });
    }
    return res;
  }
}
