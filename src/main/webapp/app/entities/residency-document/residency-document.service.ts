import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IResidencyDocument } from 'app/shared/model/residency-document.model';

type EntityResponseType = HttpResponse<IResidencyDocument>;
type EntityArrayResponseType = HttpResponse<IResidencyDocument[]>;

@Injectable({ providedIn: 'root' })
export class ResidencyDocumentService {
  public resourceUrl = SERVER_API_URL + 'api/residency-documents';

  constructor(protected http: HttpClient) {}

  create(residencyDocument: IResidencyDocument): Observable<EntityResponseType> {
    return this.http.post<IResidencyDocument>(this.resourceUrl, residencyDocument, { observe: 'response' });
  }

  update(residencyDocument: IResidencyDocument): Observable<EntityResponseType> {
    return this.http.put<IResidencyDocument>(this.resourceUrl, residencyDocument, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IResidencyDocument>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IResidencyDocument[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
