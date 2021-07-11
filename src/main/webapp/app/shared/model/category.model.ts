import { IFinancialInfo } from 'app/shared/model/financial-info.model';

export interface ICategory {
  id?: number;
  categoryNameFR?: string;
  categoryNameEN?: string;
  financialInfos?: IFinancialInfo[];
}

export class Category implements ICategory {
  constructor(
    public id?: number,
    public categoryNameFR?: string,
    public categoryNameEN?: string,
    public financialInfos?: IFinancialInfo[]
  ) {}
}
