export interface IFinancialInfo {
  id?: number;
  activityId?: number;
  categoryId?: number;
  personalInfoId?: number;
  monthlyNetIncomeId?: number;
}

export class FinancialInfo implements IFinancialInfo {
  constructor(
    public id?: number,
    public activityId?: number,
    public categoryId?: number,
    public personalInfoId?: number,
    public monthlyNetIncomeId?: number
  ) {}
}
