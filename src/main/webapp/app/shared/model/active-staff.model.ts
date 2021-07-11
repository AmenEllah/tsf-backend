export interface IActiveStaff {
  id?: number;
  matricule?: number;
  idBu?: number;
  idRole?: number;
  email?: string;
  idPoste?: number;
}

export class ActiveStaff implements IActiveStaff {
  constructor(
    public id?: number,
    public matricule?: number,
    public idBu?: number,
    public idRole?: number,
    public email?: string,
    public idPoste?: number
  ) {}
}
