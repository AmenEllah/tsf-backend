import { IOtherRevenuFile } from 'app/shared/model/other-revenu-file.model';

export interface IJustifRevenu {
  id?: number;
  labelEN?: string;
  labelFR?: string;
  otherRevenuFiles?: IOtherRevenuFile[];
}

export class JustifRevenu implements IJustifRevenu {
  constructor(public id?: number, public labelEN?: string, public labelFR?: string, public otherRevenuFiles?: IOtherRevenuFile[]) {}
}
