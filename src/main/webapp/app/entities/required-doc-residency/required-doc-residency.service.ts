import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IRequiredDocResidency } from 'app/shared/model/required-doc-residency.model';

type EntityResponseType = HttpResponse<IRequiredDocResidency>;
type EntityArrayResponseType = HttpResponse<IRequiredDocResidency[]>;

@Injectable({ providedIn: 'root' })
export class RequiredDocResidencyService {
  public resourceUrl = SERVER_API_URL + 'api/required-doc-residencies';

  constructor(protected http: HttpClient) {}

  create(requiredDocResidency: IRequiredDocResidency): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(requiredDocResidency);
    return this.http
      .post<IRequiredDocResidency>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(requiredDocResidency: IRequiredDocResidency): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(requiredDocResidency);
    return this.http
      .put<IRequiredDocResidency>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IRequiredDocResidency>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IRequiredDocResidency[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(requiredDocResidency: IRequiredDocResidency): IRequiredDocResidency {
    const copy: IRequiredDocResidency = Object.assign({}, requiredDocResidency, {
      deliveryDate:
        requiredDocResidency.deliveryDate && requiredDocResidency.deliveryDate.isValid()
          ? requiredDocResidency.deliveryDate.format(DATE_FORMAT)
          : undefined,
      experationDate:
        requiredDocResidency.experationDate && requiredDocResidency.experationDate.isValid()
          ? requiredDocResidency.experationDate.format(DATE_FORMAT)
          : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.deliveryDate = res.body.deliveryDate ? moment(res.body.deliveryDate) : undefined;
      res.body.experationDate = res.body.experationDate ? moment(res.body.experationDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((requiredDocResidency: IRequiredDocResidency) => {
        requiredDocResidency.deliveryDate = requiredDocResidency.deliveryDate ? moment(requiredDocResidency.deliveryDate) : undefined;
        requiredDocResidency.experationDate = requiredDocResidency.experationDate ? moment(requiredDocResidency.experationDate) : undefined;
      });
    }
    return res;
  }
}
