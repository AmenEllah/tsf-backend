import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IStaffPermission } from 'app/shared/model/staff-permission.model';

type EntityResponseType = HttpResponse<IStaffPermission>;
type EntityArrayResponseType = HttpResponse<IStaffPermission[]>;

@Injectable({ providedIn: 'root' })
export class StaffPermissionService {
  public resourceUrl = SERVER_API_URL + 'api/staff-permissions';

  constructor(protected http: HttpClient) {}

  create(staffPermission: IStaffPermission): Observable<EntityResponseType> {
    return this.http.post<IStaffPermission>(this.resourceUrl, staffPermission, { observe: 'response' });
  }

  update(staffPermission: IStaffPermission): Observable<EntityResponseType> {
    return this.http.put<IStaffPermission>(this.resourceUrl, staffPermission, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IStaffPermission>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IStaffPermission[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
