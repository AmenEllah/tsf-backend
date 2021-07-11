import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ISubcontractingStaff } from 'app/shared/model/subcontracting-staff.model';

type EntityResponseType = HttpResponse<ISubcontractingStaff>;
type EntityArrayResponseType = HttpResponse<ISubcontractingStaff[]>;

@Injectable({ providedIn: 'root' })
export class SubcontractingStaffService {
  public resourceUrl = SERVER_API_URL + 'api/subcontracting-staffs';

  constructor(protected http: HttpClient) {}

  create(subcontractingStaff: ISubcontractingStaff): Observable<EntityResponseType> {
    return this.http.post<ISubcontractingStaff>(this.resourceUrl, subcontractingStaff, { observe: 'response' });
  }

  update(subcontractingStaff: ISubcontractingStaff): Observable<EntityResponseType> {
    return this.http.put<ISubcontractingStaff>(this.resourceUrl, subcontractingStaff, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ISubcontractingStaff>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ISubcontractingStaff[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
