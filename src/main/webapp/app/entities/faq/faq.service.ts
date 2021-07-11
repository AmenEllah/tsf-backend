import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IFAQ } from 'app/shared/model/faq.model';

type EntityResponseType = HttpResponse<IFAQ>;
type EntityArrayResponseType = HttpResponse<IFAQ[]>;

@Injectable({ providedIn: 'root' })
export class FAQService {
  public resourceUrl = SERVER_API_URL + 'api/faqs';

  constructor(protected http: HttpClient) {}

  create(fAQ: IFAQ): Observable<EntityResponseType> {
    return this.http.post<IFAQ>(this.resourceUrl, fAQ, { observe: 'response' });
  }

  update(fAQ: IFAQ): Observable<EntityResponseType> {
    return this.http.put<IFAQ>(this.resourceUrl, fAQ, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IFAQ>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IFAQ[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
