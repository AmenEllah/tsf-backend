import { IAgency } from 'app/shared/model/agency.model';

export interface IMunicipality {
  id?: number;
  name?: string;
  agencies?: IAgency[];
  governorateId?: number;
}

export class Municipality implements IMunicipality {
  constructor(public id?: number, public name?: string, public agencies?: IAgency[], public governorateId?: number) {}
}
