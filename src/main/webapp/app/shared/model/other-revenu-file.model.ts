export interface IOtherRevenuFile {
  id?: number;
  path?: string;
  requiredDocId?: number;
  justifRevenuId?: number;
}

export class OtherRevenuFile implements IOtherRevenuFile {
  constructor(public id?: number, public path?: string, public requiredDocId?: number, public justifRevenuId?: number) {}
}
