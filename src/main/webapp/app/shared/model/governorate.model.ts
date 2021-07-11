import { IMunicipality } from 'app/shared/model/municipality.model';

export interface IGovernorate {
  id?: number;
  name?: string;
  municipalities?: IMunicipality[];
}

export class Governorate implements IGovernorate {
  constructor(public id?: number, public name?: string, public municipalities?: IMunicipality[]) {}
}
