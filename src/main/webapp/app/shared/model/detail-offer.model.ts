import { IOffer } from 'app/shared/model/offer.model';

export interface IDetailOffer {
  id?: number;
  description?: string;
  offers?: IOffer[];
}

export class DetailOffer implements IDetailOffer {
  constructor(public id?: number, public description?: string, public offers?: IOffer[]) {}
}
