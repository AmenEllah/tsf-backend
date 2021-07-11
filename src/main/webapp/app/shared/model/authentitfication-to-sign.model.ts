import { Moment } from 'moment';

export interface IAuthentitficationToSign {
  id?: number;
  email?: string;
  token?: string;
  dateCreation?: Moment;
  valide?: boolean;
}

export class AuthentitficationToSign implements IAuthentitficationToSign {
  constructor(public id?: number, public email?: string, public token?: string, public dateCreation?: Moment, public valide?: boolean) {
    this.valide = this.valide || false;
  }
}
