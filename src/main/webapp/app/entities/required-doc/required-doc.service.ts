import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IRequiredDoc } from 'app/shared/model/required-doc.model';

type EntityResponseType = HttpResponse<IRequiredDoc>;
type EntityArrayResponseType = HttpResponse<IRequiredDoc[]>;

@Injectable({ providedIn: 'root' })
export class RequiredDocService {
  public resourceUrl = SERVER_API_URL + 'api/required-docs';

  constructor(protected http: HttpClient) {}

  create(requiredDoc: IRequiredDoc): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(requiredDoc);
    return this.http
      .post<IRequiredDoc>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(requiredDoc: IRequiredDoc): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(requiredDoc);
    return this.http
      .put<IRequiredDoc>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IRequiredDoc>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IRequiredDoc[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(requiredDoc: IRequiredDoc): IRequiredDoc {
    const copy: IRequiredDoc = Object.assign({}, requiredDoc, {
      deliveryDateCin:
        requiredDoc.deliveryDateCin && requiredDoc.deliveryDateCin.isValid() ? requiredDoc.deliveryDateCin.format(DATE_FORMAT) : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.deliveryDateCin = res.body.deliveryDateCin ? moment(res.body.deliveryDateCin) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((requiredDoc: IRequiredDoc) => {
        requiredDoc.deliveryDateCin = requiredDoc.deliveryDateCin ? moment(requiredDoc.deliveryDateCin) : undefined;
      });
    }
    return res;
  }
}
