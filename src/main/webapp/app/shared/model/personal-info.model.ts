import { Moment } from 'moment';
import { Civility } from 'app/shared/model/enumerations/civility.model';

export interface IPersonalInfo {
  id?: number;
  civility?: Civility;
  firstName?: string;
  lastName?: string;
  email?: string;
  nativeCountry?: string;
  birthday?: Moment;
  clientABT?: boolean;
  rib?: string;
  nationality?: string;
  secondNationality?: string;
  nbrkids?: number;
  maritalStatus?: string;
  phone?: string;
  americanIndex?: boolean;
  accountNumber?: string;
  cin?: string;
  abroadResidancyProof?: string;
  abroadResidancyNumber?: string;
  cinDeliveryDate?: Moment;
  abroadResidancyDeliveryDate?: Moment;
  abroadResidancyExpirationDate?: Moment;
  requestId?: number;
  adressInfoId?: number;
  agencyId?: number;
  financialInfoId?: number;
}

export class PersonalInfo implements IPersonalInfo {
  constructor(
    public id?: number,
    public civility?: Civility,
    public firstName?: string,
    public lastName?: string,
    public email?: string,
    public nativeCountry?: string,
    public birthday?: Moment,
    public clientABT?: boolean,
    public rib?: string,
    public nationality?: string,
    public secondNationality?: string,
    public nbrkids?: number,
    public maritalStatus?: string,
    public phone?: string,
    public americanIndex?: boolean,
    public accountNumber?: string,
    public cin?: string,
    public abroadResidancyProof?: string,
    public abroadResidancyNumber?: string,
    public cinDeliveryDate?: Moment,
    public abroadResidancyDeliveryDate?: Moment,
    public abroadResidancyExpirationDate?: Moment,
    public requestId?: number,
    public adressInfoId?: number,
    public agencyId?: number,
    public financialInfoId?: number
  ) {
    this.clientABT = this.clientABT || false;
    this.americanIndex = this.americanIndex || false;
  }
}
