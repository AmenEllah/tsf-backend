import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IMonthlyNetIncome } from 'app/shared/model/monthly-net-income.model';

type EntityResponseType = HttpResponse<IMonthlyNetIncome>;
type EntityArrayResponseType = HttpResponse<IMonthlyNetIncome[]>;

@Injectable({ providedIn: 'root' })
export class MonthlyNetIncomeService {
  public resourceUrl = SERVER_API_URL + 'api/monthly-net-incomes';

  constructor(protected http: HttpClient) {}

  create(monthlyNetIncome: IMonthlyNetIncome): Observable<EntityResponseType> {
    return this.http.post<IMonthlyNetIncome>(this.resourceUrl, monthlyNetIncome, { observe: 'response' });
  }

  update(monthlyNetIncome: IMonthlyNetIncome): Observable<EntityResponseType> {
    return this.http.put<IMonthlyNetIncome>(this.resourceUrl, monthlyNetIncome, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMonthlyNetIncome>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMonthlyNetIncome[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
