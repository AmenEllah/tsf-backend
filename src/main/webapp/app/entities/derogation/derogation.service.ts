import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IDerogation } from 'app/shared/model/derogation.model';

type EntityResponseType = HttpResponse<IDerogation>;
type EntityArrayResponseType = HttpResponse<IDerogation[]>;

@Injectable({ providedIn: 'root' })
export class DerogationService {
  public resourceUrl = SERVER_API_URL + 'api/derogations';

  constructor(protected http: HttpClient) {}

  create(derogation: IDerogation): Observable<EntityResponseType> {
    return this.http.post<IDerogation>(this.resourceUrl, derogation, { observe: 'response' });
  }

  update(derogation: IDerogation): Observable<EntityResponseType> {
    return this.http.put<IDerogation>(this.resourceUrl, derogation, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IDerogation>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IDerogation[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
