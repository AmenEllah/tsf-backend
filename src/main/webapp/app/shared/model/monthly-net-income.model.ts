export interface IMonthlyNetIncome {
  id?: number;
  incomesFR?: string;
  incomesEN?: string;
}

export class MonthlyNetIncome implements IMonthlyNetIncome {
  constructor(public id?: number, public incomesFR?: string, public incomesEN?: string) {}
}
