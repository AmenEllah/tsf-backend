import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { INationality } from 'app/shared/model/nationality.model';

type EntityResponseType = HttpResponse<INationality>;
type EntityArrayResponseType = HttpResponse<INationality[]>;

@Injectable({ providedIn: 'root' })
export class NationalityService {
  public resourceUrl = SERVER_API_URL + 'api/nationalities';

  constructor(protected http: HttpClient) {}

  create(nationality: INationality): Observable<EntityResponseType> {
    return this.http.post<INationality>(this.resourceUrl, nationality, { observe: 'response' });
  }

  update(nationality: INationality): Observable<EntityResponseType> {
    return this.http.put<INationality>(this.resourceUrl, nationality, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<INationality>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<INationality[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
