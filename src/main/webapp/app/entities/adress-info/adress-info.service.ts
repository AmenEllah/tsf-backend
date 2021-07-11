import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IAdressInfo } from 'app/shared/model/adress-info.model';

type EntityResponseType = HttpResponse<IAdressInfo>;
type EntityArrayResponseType = HttpResponse<IAdressInfo[]>;

@Injectable({ providedIn: 'root' })
export class AdressInfoService {
  public resourceUrl = SERVER_API_URL + 'api/adress-infos';

  constructor(protected http: HttpClient) {}

  create(adressInfo: IAdressInfo): Observable<EntityResponseType> {
    return this.http.post<IAdressInfo>(this.resourceUrl, adressInfo, { observe: 'response' });
  }

  update(adressInfo: IAdressInfo): Observable<EntityResponseType> {
    return this.http.put<IAdressInfo>(this.resourceUrl, adressInfo, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IAdressInfo>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IAdressInfo[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
