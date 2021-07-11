import { IPersonalInfo } from 'app/shared/model/personal-info.model';

export interface IAgency {
  id?: number;
  name?: string;
  address?: string;
  zip?: number;
  city?: string;
  municipalityId?: number;
  personalInfos?: IPersonalInfo[];
}

export class Agency implements IAgency {
  constructor(
    public id?: number,
    public name?: string,
    public address?: string,
    public zip?: number,
    public city?: string,
    public municipalityId?: number,
    public personalInfos?: IPersonalInfo[]
  ) {}
}
