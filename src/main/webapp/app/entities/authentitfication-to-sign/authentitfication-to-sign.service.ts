import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IAuthentitficationToSign } from 'app/shared/model/authentitfication-to-sign.model';

type EntityResponseType = HttpResponse<IAuthentitficationToSign>;
type EntityArrayResponseType = HttpResponse<IAuthentitficationToSign[]>;

@Injectable({ providedIn: 'root' })
export class AuthentitficationToSignService {
  public resourceUrl = SERVER_API_URL + 'api/authentitfication-to-signs';

  constructor(protected http: HttpClient) {}

  create(authentitficationToSign: IAuthentitficationToSign): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(authentitficationToSign);
    return this.http
      .post<IAuthentitficationToSign>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(authentitficationToSign: IAuthentitficationToSign): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(authentitficationToSign);
    return this.http
      .put<IAuthentitficationToSign>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IAuthentitficationToSign>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IAuthentitficationToSign[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(authentitficationToSign: IAuthentitficationToSign): IAuthentitficationToSign {
    const copy: IAuthentitficationToSign = Object.assign({}, authentitficationToSign, {
      dateCreation:
        authentitficationToSign.dateCreation && authentitficationToSign.dateCreation.isValid()
          ? authentitficationToSign.dateCreation.format(DATE_FORMAT)
          : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dateCreation = res.body.dateCreation ? moment(res.body.dateCreation) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((authentitficationToSign: IAuthentitficationToSign) => {
        authentitficationToSign.dateCreation = authentitficationToSign.dateCreation
          ? moment(authentitficationToSign.dateCreation)
          : undefined;
      });
    }
    return res;
  }
}
