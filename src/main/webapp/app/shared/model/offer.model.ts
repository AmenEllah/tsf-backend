import { IRequest } from 'app/shared/model/request.model';
import { IDetailOffer } from 'app/shared/model/detail-offer.model';

export interface IOffer {
  id?: number;
  name?: string;
  price?: number;
  url?: string;
  requests?: IRequest[];
  detailOffers?: IDetailOffer[];
}

export class Offer implements IOffer {
  constructor(
    public id?: number,
    public name?: string,
    public price?: number,
    public url?: string,
    public requests?: IRequest[],
    public detailOffers?: IDetailOffer[]
  ) {}
}
