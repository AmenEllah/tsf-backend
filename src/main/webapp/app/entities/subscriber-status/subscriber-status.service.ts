import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ISubscriberStatus } from 'app/shared/model/subscriber-status.model';

type EntityResponseType = HttpResponse<ISubscriberStatus>;
type EntityArrayResponseType = HttpResponse<ISubscriberStatus[]>;

@Injectable({ providedIn: 'root' })
export class SubscriberStatusService {
  public resourceUrl = SERVER_API_URL + 'api/subscriber-statuses';

  constructor(protected http: HttpClient) {}

  create(subscriberStatus: ISubscriberStatus): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(subscriberStatus);
    return this.http
      .post<ISubscriberStatus>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(subscriberStatus: ISubscriberStatus): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(subscriberStatus);
    return this.http
      .put<ISubscriberStatus>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ISubscriberStatus>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ISubscriberStatus[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(subscriberStatus: ISubscriberStatus): ISubscriberStatus {
    const copy: ISubscriberStatus = Object.assign({}, subscriberStatus, {
      dateAppelVisio:
        subscriberStatus.dateAppelVisio && subscriberStatus.dateAppelVisio.isValid()
          ? subscriberStatus.dateAppelVisio.format(DATE_FORMAT)
          : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dateAppelVisio = res.body.dateAppelVisio ? moment(res.body.dateAppelVisio) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((subscriberStatus: ISubscriberStatus) => {
        subscriberStatus.dateAppelVisio = subscriberStatus.dateAppelVisio ? moment(subscriberStatus.dateAppelVisio) : undefined;
      });
    }
    return res;
  }
}
