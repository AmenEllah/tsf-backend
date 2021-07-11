import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IJustifRevenu } from 'app/shared/model/justif-revenu.model';

type EntityResponseType = HttpResponse<IJustifRevenu>;
type EntityArrayResponseType = HttpResponse<IJustifRevenu[]>;

@Injectable({ providedIn: 'root' })
export class JustifRevenuService {
  public resourceUrl = SERVER_API_URL + 'api/justif-revenus';

  constructor(protected http: HttpClient) {}

  create(justifRevenu: IJustifRevenu): Observable<EntityResponseType> {
    return this.http.post<IJustifRevenu>(this.resourceUrl, justifRevenu, { observe: 'response' });
  }

  update(justifRevenu: IJustifRevenu): Observable<EntityResponseType> {
    return this.http.put<IJustifRevenu>(this.resourceUrl, justifRevenu, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IJustifRevenu>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IJustifRevenu[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
