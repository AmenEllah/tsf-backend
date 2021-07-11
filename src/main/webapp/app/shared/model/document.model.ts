import { Moment } from 'moment';

export interface IDocument {
  id?: number;
  typeDocument?: string;
  nomFichier?: string;
  emplacement?: string;
  idDossierSigned?: string;
  hasSigned?: boolean;
  dateCreation?: Moment;
  dateUpdate?: Moment;
  requestId?: number;
}

export class Document implements IDocument {
  constructor(
    public id?: number,
    public typeDocument?: string,
    public nomFichier?: string,
    public emplacement?: string,
    public idDossierSigned?: string,
    public hasSigned?: boolean,
    public dateCreation?: Moment,
    public dateUpdate?: Moment,
    public requestId?: number
  ) {
    this.hasSigned = this.hasSigned || false;
  }
}
