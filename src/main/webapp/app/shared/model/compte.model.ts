import { Moment } from 'moment';

export interface ICompte {
  id?: number;
  nom?: string;
  prenom?: string;
  dateDeNaissance?: Moment;
  email?: string;
  telephone?: number;
}

export class Compte implements ICompte {
  constructor(
    public id?: number,
    public nom?: string,
    public prenom?: string,
    public dateDeNaissance?: Moment,
    public email?: string,
    public telephone?: number
  ) {}
}
