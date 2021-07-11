export interface ISupplyMatrix {
  id?: number;
  categoryId?: number;
  incomeTypeCode?: string;
  incomeType?: string;
  monthlyIncomeId?: number;
  marketCode?: number;
  market?: string;
  segmentCode?: string;
  segment?: string;
  activityId?: number;
  professionCode?: string;
  profession?: string;
}

export class SupplyMatrix implements ISupplyMatrix {
  constructor(
    public id?: number,
    public categoryId?: number,
    public incomeTypeCode?: string,
    public incomeType?: string,
    public monthlyIncomeId?: number,
    public marketCode?: number,
    public market?: string,
    public segmentCode?: string,
    public segment?: string,
    public activityId?: number,
    public professionCode?: string,
    public profession?: string
  ) {}
}
