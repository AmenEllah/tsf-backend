import { Moment } from 'moment';
import { IRequiredDocIncome } from 'app/shared/model/required-doc-income.model';
import { IRequiredDocResidency } from 'app/shared/model/required-doc-residency.model';
import { IOtherResidencyFile } from 'app/shared/model/other-residency-file.model';
import { IOtherRevenuFile } from 'app/shared/model/other-revenu-file.model';

export interface IRequiredDoc {
  id?: number;
  label?: string;
  type?: string;
  numCIN?: string;
  deliveryDateCin?: Moment;
  rectoCin?: string;
  versoCin?: string;
  fatca?: string;
  requestId?: number;
  requiredDocIncomes?: IRequiredDocIncome[];
  requiredDocResidencies?: IRequiredDocResidency[];
  otherResidencyFiles?: IOtherResidencyFile[];
  otherRevenuFiles?: IOtherRevenuFile[];
}

export class RequiredDoc implements IRequiredDoc {
  constructor(
    public id?: number,
    public label?: string,
    public type?: string,
    public numCIN?: string,
    public deliveryDateCin?: Moment,
    public rectoCin?: string,
    public versoCin?: string,
    public fatca?: string,
    public requestId?: number,
    public requiredDocIncomes?: IRequiredDocIncome[],
    public requiredDocResidencies?: IRequiredDocResidency[],
    public otherResidencyFiles?: IOtherResidencyFile[],
    public otherRevenuFiles?: IOtherRevenuFile[]
  ) {}
}
