import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IRequiredDocIncome } from 'app/shared/model/required-doc-income.model';

type EntityResponseType = HttpResponse<IRequiredDocIncome>;
type EntityArrayResponseType = HttpResponse<IRequiredDocIncome[]>;

@Injectable({ providedIn: 'root' })
export class RequiredDocIncomeService {
  public resourceUrl = SERVER_API_URL + 'api/required-doc-incomes';

  constructor(protected http: HttpClient) {}

  create(requiredDocIncome: IRequiredDocIncome): Observable<EntityResponseType> {
    return this.http.post<IRequiredDocIncome>(this.resourceUrl, requiredDocIncome, { observe: 'response' });
  }

  update(requiredDocIncome: IRequiredDocIncome): Observable<EntityResponseType> {
    return this.http.put<IRequiredDocIncome>(this.resourceUrl, requiredDocIncome, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IRequiredDocIncome>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IRequiredDocIncome[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
