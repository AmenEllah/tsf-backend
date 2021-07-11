import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IAppSettings } from 'app/shared/model/app-settings.model';

type EntityResponseType = HttpResponse<IAppSettings>;
type EntityArrayResponseType = HttpResponse<IAppSettings[]>;

@Injectable({ providedIn: 'root' })
export class AppSettingsService {
  public resourceUrl = SERVER_API_URL + 'api/app-settings';

  constructor(protected http: HttpClient) {}

  create(appSettings: IAppSettings): Observable<EntityResponseType> {
    return this.http.post<IAppSettings>(this.resourceUrl, appSettings, { observe: 'response' });
  }

  update(appSettings: IAppSettings): Observable<EntityResponseType> {
    return this.http.put<IAppSettings>(this.resourceUrl, appSettings, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IAppSettings>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IAppSettings[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
