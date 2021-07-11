import { IOtherResidencyFile } from 'app/shared/model/other-residency-file.model';

export interface IResidencyDocument {
  id?: number;
  labelFR?: string;
  labelEN?: string;
  requiredDocResidencyId?: number;
  otherResidencyFiles?: IOtherResidencyFile[];
}

export class ResidencyDocument implements IResidencyDocument {
  constructor(
    public id?: number,
    public labelFR?: string,
    public labelEN?: string,
    public requiredDocResidencyId?: number,
    public otherResidencyFiles?: IOtherResidencyFile[]
  ) {}
}
