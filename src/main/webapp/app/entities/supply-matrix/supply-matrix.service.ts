import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ISupplyMatrix } from 'app/shared/model/supply-matrix.model';

type EntityResponseType = HttpResponse<ISupplyMatrix>;
type EntityArrayResponseType = HttpResponse<ISupplyMatrix[]>;

@Injectable({ providedIn: 'root' })
export class SupplyMatrixService {
  public resourceUrl = SERVER_API_URL + 'api/supply-matrices';

  constructor(protected http: HttpClient) {}

  create(supplyMatrix: ISupplyMatrix): Observable<EntityResponseType> {
    return this.http.post<ISupplyMatrix>(this.resourceUrl, supplyMatrix, { observe: 'response' });
  }

  update(supplyMatrix: ISupplyMatrix): Observable<EntityResponseType> {
    return this.http.put<ISupplyMatrix>(this.resourceUrl, supplyMatrix, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ISupplyMatrix>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ISupplyMatrix[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
