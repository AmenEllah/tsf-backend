import { RequestAffectation } from 'app/shared/model/enumerations/request-affectation.model';

export interface IDerogation {
  id?: number;
  message?: string;
  affectation?: RequestAffectation;
  matricule?: string;
  requestId?: number;
}

export class Derogation implements IDerogation {
  constructor(
    public id?: number,
    public message?: string,
    public affectation?: RequestAffectation,
    public matricule?: string,
    public requestId?: number
  ) {}
}
