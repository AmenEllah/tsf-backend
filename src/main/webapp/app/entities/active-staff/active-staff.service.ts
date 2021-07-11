import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IActiveStaff } from 'app/shared/model/active-staff.model';

type EntityResponseType = HttpResponse<IActiveStaff>;
type EntityArrayResponseType = HttpResponse<IActiveStaff[]>;

@Injectable({ providedIn: 'root' })
export class ActiveStaffService {
  public resourceUrl = SERVER_API_URL + 'api/active-staffs';

  constructor(protected http: HttpClient) {}

  create(activeStaff: IActiveStaff): Observable<EntityResponseType> {
    return this.http.post<IActiveStaff>(this.resourceUrl, activeStaff, { observe: 'response' });
  }

  update(activeStaff: IActiveStaff): Observable<EntityResponseType> {
    return this.http.put<IActiveStaff>(this.resourceUrl, activeStaff, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IActiveStaff>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IActiveStaff[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
