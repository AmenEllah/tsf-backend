import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IOtherResidencyFile } from 'app/shared/model/other-residency-file.model';

type EntityResponseType = HttpResponse<IOtherResidencyFile>;
type EntityArrayResponseType = HttpResponse<IOtherResidencyFile[]>;

@Injectable({ providedIn: 'root' })
export class OtherResidencyFileService {
  public resourceUrl = SERVER_API_URL + 'api/other-residency-files';

  constructor(protected http: HttpClient) {}

  create(otherResidencyFile: IOtherResidencyFile): Observable<EntityResponseType> {
    return this.http.post<IOtherResidencyFile>(this.resourceUrl, otherResidencyFile, { observe: 'response' });
  }

  update(otherResidencyFile: IOtherResidencyFile): Observable<EntityResponseType> {
    return this.http.put<IOtherResidencyFile>(this.resourceUrl, otherResidencyFile, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IOtherResidencyFile>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IOtherResidencyFile[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
