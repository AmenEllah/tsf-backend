import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IDetailOffer } from 'app/shared/model/detail-offer.model';

type EntityResponseType = HttpResponse<IDetailOffer>;
type EntityArrayResponseType = HttpResponse<IDetailOffer[]>;

@Injectable({ providedIn: 'root' })
export class DetailOfferService {
  public resourceUrl = SERVER_API_URL + 'api/detail-offers';

  constructor(protected http: HttpClient) {}

  create(detailOffer: IDetailOffer): Observable<EntityResponseType> {
    return this.http.post<IDetailOffer>(this.resourceUrl, detailOffer, { observe: 'response' });
  }

  update(detailOffer: IDetailOffer): Observable<EntityResponseType> {
    return this.http.put<IDetailOffer>(this.resourceUrl, detailOffer, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IDetailOffer>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IDetailOffer[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
