import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IRequest } from 'app/shared/model/request.model';

type EntityResponseType = HttpResponse<IRequest>;
type EntityArrayResponseType = HttpResponse<IRequest[]>;

@Injectable({ providedIn: 'root' })
export class RequestService {
  public resourceUrl = SERVER_API_URL + 'api/requests';

  constructor(protected http: HttpClient) {}

  create(request: IRequest): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(request);
    return this.http
      .post<IRequest>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(request: IRequest): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(request);
    return this.http
      .put<IRequest>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IRequest>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IRequest[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(request: IRequest): IRequest {
    const copy: IRequest = Object.assign({}, request, {
      visioDate: request.visioDate && request.visioDate.isValid() ? request.visioDate.format(DATE_FORMAT) : undefined,
      sendingMailDate:
        request.sendingMailDate && request.sendingMailDate.isValid() ? request.sendingMailDate.format(DATE_FORMAT) : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.visioDate = res.body.visioDate ? moment(res.body.visioDate) : undefined;
      res.body.sendingMailDate = res.body.sendingMailDate ? moment(res.body.sendingMailDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((request: IRequest) => {
        request.visioDate = request.visioDate ? moment(request.visioDate) : undefined;
        request.sendingMailDate = request.sendingMailDate ? moment(request.sendingMailDate) : undefined;
      });
    }
    return res;
  }
}
