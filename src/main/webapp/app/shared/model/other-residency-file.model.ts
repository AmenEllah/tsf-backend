export interface IOtherResidencyFile {
  id?: number;
  path?: string;
  requiredDocId?: number;
  residencyDocumentId?: number;
}

export class OtherResidencyFile implements IOtherResidencyFile {
  constructor(public id?: number, public path?: string, public requiredDocId?: number, public residencyDocumentId?: number) {}
}
