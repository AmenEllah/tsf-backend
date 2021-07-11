export interface IRequestBankAccount {
  id?: number;
}

export class Request implements IRequestBankAccount {
  constructor(public id?: number) {}
}
