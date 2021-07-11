export interface ISubcontractingStaff {
  id?: number;
  matricule?: number;
  idBu?: number;
  idRole?: number;
  email?: string;
  idPoste?: number;
}

export class SubcontractingStaff implements ISubcontractingStaff {
  constructor(
    public id?: number,
    public matricule?: number,
    public idBu?: number,
    public idRole?: number,
    public email?: string,
    public idPoste?: number
  ) {}
}
