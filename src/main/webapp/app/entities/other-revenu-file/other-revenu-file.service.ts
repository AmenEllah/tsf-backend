import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IOtherRevenuFile } from 'app/shared/model/other-revenu-file.model';

type EntityResponseType = HttpResponse<IOtherRevenuFile>;
type EntityArrayResponseType = HttpResponse<IOtherRevenuFile[]>;

@Injectable({ providedIn: 'root' })
export class OtherRevenuFileService {
  public resourceUrl = SERVER_API_URL + 'api/other-revenu-files';

  constructor(protected http: HttpClient) {}

  create(otherRevenuFile: IOtherRevenuFile): Observable<EntityResponseType> {
    return this.http.post<IOtherRevenuFile>(this.resourceUrl, otherRevenuFile, { observe: 'response' });
  }

  update(otherRevenuFile: IOtherRevenuFile): Observable<EntityResponseType> {
    return this.http.put<IOtherRevenuFile>(this.resourceUrl, otherRevenuFile, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IOtherRevenuFile>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IOtherRevenuFile[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
