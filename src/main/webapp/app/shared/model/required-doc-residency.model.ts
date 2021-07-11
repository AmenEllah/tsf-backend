import { Moment } from 'moment';

export interface IRequiredDocResidency {
  id?: number;
  type?: string;
  num?: string;
  deliveryDate?: Moment;
  experationDate?: Moment;
  illimitedExperationDate?: boolean;
  residencyRecto?: string;
  residencyVerso?: string;
  requiredDocId?: number;
  residencyDocumentId?: number;
}

export class RequiredDocResidency implements IRequiredDocResidency {
  constructor(
    public id?: number,
    public type?: string,
    public num?: string,
    public deliveryDate?: Moment,
    public experationDate?: Moment,
    public illimitedExperationDate?: boolean,
    public residencyRecto?: string,
    public residencyVerso?: string,
    public requiredDocId?: number,
    public residencyDocumentId?: number
  ) {
    this.illimitedExperationDate = this.illimitedExperationDate || false;
  }
}
