export interface IAdressInfo {
  id?: number;
  countryOfResidence?: string;
  address?: string;
  zip?: number;
  city?: string;
  personalInfoId?: number;
}

export class AdressInfo implements IAdressInfo {
  constructor(
    public id?: number,
    public countryOfResidence?: string,
    public address?: string,
    public zip?: number,
    public city?: string,
    public personalInfoId?: number
  ) {}
}
