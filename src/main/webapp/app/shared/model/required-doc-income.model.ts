export interface IRequiredDocIncome {
  id?: number;
  type?: string;
  path?: string;
  requiredDocId?: number;
}

export class RequiredDocIncome implements IRequiredDocIncome {
  constructor(public id?: number, public type?: string, public path?: string, public requiredDocId?: number) {}
}
