export interface ICountry {
  id?: number;
  nameFR?: string;
  nameEN?: string;
  code?: string;
}

export class Country implements ICountry {
  constructor(public id?: number, public nameFR?: string, public nameEN?: string, public code?: string) {}
}
