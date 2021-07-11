import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IPersonalInfo } from 'app/shared/model/personal-info.model';

type EntityResponseType = HttpResponse<IPersonalInfo>;
type EntityArrayResponseType = HttpResponse<IPersonalInfo[]>;

@Injectable({ providedIn: 'root' })
export class PersonalInfoService {
  public resourceUrl = SERVER_API_URL + 'api/personal-infos';

  constructor(protected http: HttpClient) {}

  create(personalInfo: IPersonalInfo): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(personalInfo);
    return this.http
      .post<IPersonalInfo>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(personalInfo: IPersonalInfo): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(personalInfo);
    return this.http
      .put<IPersonalInfo>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IPersonalInfo>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IPersonalInfo[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(personalInfo: IPersonalInfo): IPersonalInfo {
    const copy: IPersonalInfo = Object.assign({}, personalInfo, {
      birthday: personalInfo.birthday && personalInfo.birthday.isValid() ? personalInfo.birthday.format(DATE_FORMAT) : undefined,
      cinDeliveryDate:
        personalInfo.cinDeliveryDate && personalInfo.cinDeliveryDate.isValid() ? personalInfo.cinDeliveryDate.toJSON() : undefined,
      abroadResidancyDeliveryDate:
        personalInfo.abroadResidancyDeliveryDate && personalInfo.abroadResidancyDeliveryDate.isValid()
          ? personalInfo.abroadResidancyDeliveryDate.toJSON()
          : undefined,
      abroadResidancyExpirationDate:
        personalInfo.abroadResidancyExpirationDate && personalInfo.abroadResidancyExpirationDate.isValid()
          ? personalInfo.abroadResidancyExpirationDate.toJSON()
          : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.birthday = res.body.birthday ? moment(res.body.birthday) : undefined;
      res.body.cinDeliveryDate = res.body.cinDeliveryDate ? moment(res.body.cinDeliveryDate) : undefined;
      res.body.abroadResidancyDeliveryDate = res.body.abroadResidancyDeliveryDate
        ? moment(res.body.abroadResidancyDeliveryDate)
        : undefined;
      res.body.abroadResidancyExpirationDate = res.body.abroadResidancyExpirationDate
        ? moment(res.body.abroadResidancyExpirationDate)
        : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((personalInfo: IPersonalInfo) => {
        personalInfo.birthday = personalInfo.birthday ? moment(personalInfo.birthday) : undefined;
        personalInfo.cinDeliveryDate = personalInfo.cinDeliveryDate ? moment(personalInfo.cinDeliveryDate) : undefined;
        personalInfo.abroadResidancyDeliveryDate = personalInfo.abroadResidancyDeliveryDate
          ? moment(personalInfo.abroadResidancyDeliveryDate)
          : undefined;
        personalInfo.abroadResidancyExpirationDate = personalInfo.abroadResidancyExpirationDate
          ? moment(personalInfo.abroadResidancyExpirationDate)
          : undefined;
      });
    }
    return res;
  }
}
