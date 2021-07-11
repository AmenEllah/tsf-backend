import { IRequestBankAccount } from 'app/shared/model/request-bank-account.model';

export interface IBankAccount {
  id?: number;
  libelleFR?: string;
  libelleEN?: string;
  descriptionFR?: string;
  descriptionEN?: string;
  requestBankAccounts?: IRequestBankAccount[];
}

export class BankAccount implements IBankAccount {
  constructor(
    public id?: number,
    public libelleFR?: string,
    public libelleEN?: string,
    public descriptionFR?: string,
    public descriptionEN?: string,
    public requestBankAccounts?: IRequestBankAccount[]
  ) {}
}
