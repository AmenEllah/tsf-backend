import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IGovernorate } from 'app/shared/model/governorate.model';

type EntityResponseType = HttpResponse<IGovernorate>;
type EntityArrayResponseType = HttpResponse<IGovernorate[]>;

@Injectable({ providedIn: 'root' })
export class GovernorateService {
  public resourceUrl = SERVER_API_URL + 'api/governorates';

  constructor(protected http: HttpClient) {}

  create(governorate: IGovernorate): Observable<EntityResponseType> {
    return this.http.post<IGovernorate>(this.resourceUrl, governorate, { observe: 'response' });
  }

  update(governorate: IGovernorate): Observable<EntityResponseType> {
    return this.http.put<IGovernorate>(this.resourceUrl, governorate, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IGovernorate>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IGovernorate[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
