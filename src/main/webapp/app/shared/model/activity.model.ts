import { IFinancialInfo } from 'app/shared/model/financial-info.model';

export interface IActivity {
  id?: number;
  activityNameFR?: string;
  activityNameEN?: string;
  financialInfos?: IFinancialInfo[];
}

export class Activity implements IActivity {
  constructor(
    public id?: number,
    public activityNameFR?: string,
    public activityNameEN?: string,
    public financialInfos?: IFinancialInfo[]
  ) {}
}
