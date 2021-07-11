import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IBotScan } from 'app/shared/model/bot-scan.model';

type EntityResponseType = HttpResponse<IBotScan>;
type EntityArrayResponseType = HttpResponse<IBotScan[]>;

@Injectable({ providedIn: 'root' })
export class BotScanService {
  public resourceUrl = SERVER_API_URL + 'api/bot-scans';

  constructor(protected http: HttpClient) {}

  create(botScan: IBotScan): Observable<EntityResponseType> {
    return this.http.post<IBotScan>(this.resourceUrl, botScan, { observe: 'response' });
  }

  update(botScan: IBotScan): Observable<EntityResponseType> {
    return this.http.put<IBotScan>(this.resourceUrl, botScan, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IBotScan>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IBotScan[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
